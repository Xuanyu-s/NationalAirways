/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.national_airways;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * A boarding-pass styled dialog shown immediately after a passenger
 * successfully joins the queue.
 *
 * @author Ace
 */
public class BoardingTicket extends JDialog {

    private static final Color PASS_BLUE = new Color(28, 74, 145);
    private static final Color LIGHT_GRAY = new Color(245, 246, 248);

    private boolean proceedClicked = false;

    public BoardingTicket(Frame owner, String passengerName, String ticketNumber,
                           String origin, String destination, int queueNumber) {
        super(owner, "Boarding Pass", true);
        buildUI(passengerName, ticketNumber, origin, destination, queueNumber);
        pack();
        setResizable(false);
        setLocationRelativeTo(owner);
    }

    private void buildUI(String passengerName, String ticketNumber,
                          String origin, String destination, int queueNumber) {

        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210)),
                new EmptyBorder(0, 0, 0, 0)));

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(PASS_BLUE);
        header.setBorder(new EmptyBorder(10, 16, 10, 16));

        JLabel plane = new JLabel("\u2708");
        plane.setForeground(Color.WHITE);
        plane.setFont(plane.getFont().deriveFont(Font.PLAIN, 20f));

        JLabel title = new JLabel("BOARDING PASS", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));

        JLabel airline = new JLabel("NATIONAL AIRWAYS", SwingConstants.RIGHT);
        airline.setForeground(new Color(220, 230, 245));
        airline.setFont(airline.getFont().deriveFont(Font.PLAIN, 10f));

        header.add(plane, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        header.add(airline, BorderLayout.EAST);

        JPanel routePanel = new JPanel(new GridBagLayout());
        routePanel.setBackground(Color.WHITE);
        routePanel.setBorder(new EmptyBorder(18, 20, 12, 20));
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = 0;
        gc.weightx = 1;

        JLabel originLabel = makeRouteCode(origin);
        JLabel arrow = new JLabel("\u2708   \u2192", SwingConstants.CENTER);
        arrow.setFont(arrow.getFont().deriveFont(Font.PLAIN, 16f));
        arrow.setForeground(new Color(120, 120, 120));
        JLabel destLabel = makeRouteCode(destination);

        gc.gridx = 0; gc.anchor = GridBagConstraints.WEST;
        routePanel.add(originLabel, gc);
        gc.gridx = 1; gc.anchor = GridBagConstraints.CENTER;
        routePanel.add(arrow, gc);
        gc.gridx = 2; gc.anchor = GridBagConstraints.EAST;
        routePanel.add(destLabel, gc);

        JPanel details = new JPanel(new GridLayout(0, 2, 12, 10));
        details.setBackground(Color.WHITE);
        details.setBorder(new EmptyBorder(4, 20, 10, 20));

        details.add(fieldBlock("PASSENGER", passengerName));
        details.add(fieldBlock("QUEUE NUMBER", String.format("%04d", queueNumber)));
        details.add(fieldBlock("TICKET NUMBER", ticketNumber));
        details.add(fieldBlock("STATUS", "WAITING"));

        DashedSeparator dashed = new DashedSeparator();
        dashed.setPreferredSize(new Dimension(10, 14));

        JPanel barcodePanel = new JPanel(new BorderLayout());
        barcodePanel.setBackground(Color.WHITE);
        barcodePanel.setBorder(new EmptyBorder(8, 20, 4, 20));
        BarcodeStrip barcode = new BarcodeStrip(ticketNumber);
        barcode.setPreferredSize(new Dimension(300, 40));
        JLabel ticketNoUnderBarcode = new JLabel(ticketNumber, SwingConstants.CENTER);
        ticketNoUnderBarcode.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        JPanel barcodeWrap = new JPanel(new BorderLayout());
        barcodeWrap.setBackground(Color.WHITE);
        barcodeWrap.add(barcode, BorderLayout.CENTER);
        barcodeWrap.add(ticketNoUnderBarcode, BorderLayout.SOUTH);
        barcodePanel.add(barcodeWrap, BorderLayout.CENTER);

        JButton proceedButton = new JButton("PROCEED");
        proceedButton.setFocusPainted(false);
        proceedButton.setBackground(PASS_BLUE);
        proceedButton.setForeground(Color.WHITE);
        proceedButton.setFont(proceedButton.getFont().deriveFont(Font.BOLD, 12f));
        proceedButton.addActionListener(e -> {
            proceedClicked = true;
            dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(0, 20, 16, 20));
        buttonPanel.add(proceedButton);

        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBackground(Color.WHITE);
        body.add(routePanel);
        body.add(details);
        body.add(dashed);
        body.add(barcodePanel);
        body.add(buttonPanel);

        card.add(header, BorderLayout.NORTH);
        card.add(body, BorderLayout.CENTER);

        getContentPane().setBackground(LIGHT_GRAY);
        getContentPane().setLayout(new BorderLayout());
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(16, 16, 16, 16));
        getContentPane().add(card, BorderLayout.CENTER);
    }

    private JLabel makeRouteCode(String text) {
        String code = text == null ? "---" : text.trim();
        if (code.length() > 3) {
            code = code.substring(0, 3).toUpperCase();
        } else {
            code = code.toUpperCase();
        }
        JLabel label = new JLabel(code);
        label.setFont(label.getFont().deriveFont(Font.BOLD, 22f));
        label.setForeground(new Color(40, 40, 40));
        return label;
    }

    private JPanel fieldBlock(String labelText, String valueText) {
        JPanel block = new JPanel();
        block.setLayout(new BoxLayout(block, BoxLayout.Y_AXIS));
        block.setBackground(Color.WHITE);

        JLabel label = new JLabel(labelText);
        label.setFont(label.getFont().deriveFont(Font.PLAIN, 9f));
        label.setForeground(new Color(140, 140, 140));

        JLabel value = new JLabel(valueText == null ? "" : valueText);
        value.setFont(value.getFont().deriveFont(Font.BOLD, 13f));
        value.setForeground(new Color(30, 30, 30));

        block.add(label);
        block.add(value);
        return block;
    }

    public boolean isProceedClicked() {
        return proceedClicked;
    }

    private static class DashedSeparator extends JComponent {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(200, 200, 200));
            g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                    10f, new float[]{6f, 5f}, 0f));
            int y = getHeight() / 2;
            g2.drawLine(20, y, getWidth() - 20, y);
            g2.dispose();
        }
    }

    private static class BarcodeStrip extends JComponent {
        private final String seed;

        BarcodeStrip(String seed) {
            this.seed = seed == null ? "0000" : seed;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(new Color(30, 30, 30));
            int x = 0;
            int w = getWidth();
            int h = getHeight() - 6;
            long hash = 0;
            for (int i = 0; i < seed.length(); i++) {
                hash = hash * 31 + seed.charAt(i);
            }
            java.util.Random rnd = new java.util.Random(hash);
            while (x < w) {
                int barWidth = 1 + rnd.nextInt(3);
                if (rnd.nextBoolean()) {
                    g2.fillRect(x, 0, barWidth, h);
                }
                x += barWidth + 1;
            }
            g2.dispose();
        }
    }
}