package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediaStore extends JPanel {
    private Media media;

    public MediaStore(Media media) {
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 15));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));


        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    JDialog playDialog = new JDialog();
                    playDialog.setTitle("Playing Media");
                    playDialog.setSize(350, 150);
                    playDialog.setModal(true);
                    playDialog.setLayout(new BorderLayout(10, 10));

                    JLabel playingLabel = new JLabel("<html><center>Now Playing:<br><b>"
                            + media.getTitle() + "</b></center></html>",
                            SwingConstants.CENTER);
                    playingLabel.setFont(new Font(playingLabel.getFont().getName(), Font.PLAIN, 14));


                    JButton okButton = new JButton("OK");
                    okButton.addActionListener(evt -> playDialog.dispose());


                    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    buttonPanel.add(okButton);

                    playDialog.add(playingLabel, BorderLayout.CENTER);
                    playDialog.add(buttonPanel, BorderLayout.SOUTH);


                    playDialog.setLocationRelativeTo(null);
                    playDialog.setVisible(true);
                }
            });
            container.add(playButton);
        }



        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}