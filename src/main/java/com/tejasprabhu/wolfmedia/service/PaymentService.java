package com.tejasprabhu.wolfmedia.service;

import com.tejasprabhu.wolfmedia.dao.*;
import com.tejasprabhu.wolfmedia.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.*;

@Service
public class PaymentService {

    @Autowired
    private SongDAO songDAO;
    @Autowired
    private CollaborationDAO collaborationDAO;
    @Autowired
    private ArtistPaymentDAO artistPaymentsDAO;
    @Autowired
    private PodcastHostPaymentDAO podcastHostPaymentsDAO;
    @Autowired
    private LabelPaymentDAO labelPaymentsDAO;
    @Autowired
    private RoyaltyPaymentDAO royaltyPaymentsDAO;
    @Autowired
    private PodcastDAO podcastDAO;

    private static final int BATCH_SIZE = 2;

    public void calculateRoyaltyPayments(int year, int month) {
        int offset = 0;
        while (true) {
            List<Song> songs = songDAO.getBatchOfSongs(BATCH_SIZE, offset);
            if (songs.isEmpty()) {
                break;
            }

            for (Song song : songs) {
                // Create an instance of MonthlyPlayCountDAO (assuming it's a class)
                MonthlyPlayCountDAO monthlyPlayCountDAO = new MonthlyPlayCountDAO();
                MonthlyPlayCount monthlyPlayCount = monthlyPlayCountDAO.getPlayCountForSongInMonth(song.getSongID(), year, month);

                if (monthlyPlayCount != null) {
                    BigDecimal royalty = BigDecimal.valueOf(monthlyPlayCount.getPlayCount()).multiply(BigDecimal.valueOf(song.getRoyaltyRate()));
                    RoyaltyPayment payment = new RoyaltyPayment(null, song.getSongID(), new Date(System.currentTimeMillis()), royalty, "Pending");
                    royaltyPaymentsDAO.save(payment);
                }
            }

            offset += BATCH_SIZE;
        }
    }

    public void distributeSongPayments() {
        int offset = 0;
        while (true) {
            List<RoyaltyPayment> royalties = royaltyPaymentsDAO.getPaymentsForCurrentMonth(BATCH_SIZE, offset);
            if (royalties.isEmpty()) {
                break;
            }

            for (RoyaltyPayment royalty : royalties) {
                BigDecimal labelPayment = royalty.getCalculatedAmount().multiply(BigDecimal.valueOf(0.30));
                BigDecimal artistPayment = royalty.getCalculatedAmount().subtract(labelPayment);

                Song song = songDAO.getSong(royalty.getSongID());
                List<Collaboration> collaborators = collaborationDAO.getCollaboratorsForSong(song.getSongID());
                BigDecimal perArtistPayment = artistPayment.divide(BigDecimal.valueOf(collaborators.size() + 1), 2, RoundingMode.HALF_UP);

                artistPaymentsDAO.save(new ArtistPayment(null, song.getArtistID(), song.getSongID(), perArtistPayment, new Date(System.currentTimeMillis())));
                for (Collaboration collaborator : collaborators) {
                    artistPaymentsDAO.save(new ArtistPayment(null, collaborator.getArtistID(), song.getSongID(), perArtistPayment, new Date(System.currentTimeMillis())));
                }
                labelPaymentsDAO.save(new LabelPayment(null, songDAO.getLabelId(song.getSongID()), song.getArtistID(), labelPayment, new Date(System.currentTimeMillis())));
            }

            offset += BATCH_SIZE;
        }
    }

    public void distributePodcastPayments(PodcastEpisode episode) {

        Map<String, Object> filters = new HashMap<>();
        filters.put("PodcastID", episode.getPodcastID());

        Podcast podcast = (Podcast) podcastDAO.findWithFilters(filters);

        BigDecimal adRevenue = BigDecimal.valueOf(episode.getAdCount()).multiply(BigDecimal.valueOf(10));
        BigDecimal totalPayment = episode.getFlatFee().add(adRevenue);

        podcastHostPaymentsDAO.save(new PodcastHostPayment(null, podcast.getHostID(), podcast.getPodcastID(), episode.getEpisodeID(), totalPayment, new Date(System.currentTimeMillis())));

    }
}