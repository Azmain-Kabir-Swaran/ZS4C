import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Class_6 implements Runnable {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Class_6());
    }

    @Override
    public void run() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new MainPanel());
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

class MainPanel extends JPanel {

    public MainPanel() {
        super(new BorderLayout());
        Model model = new Model();
        View view = new View(model);
        Control control = new Control(model, view);
        JLabel label = new JLabel("Guess what color!", JLabel.CENTER);
        this.add(label, BorderLayout.NORTH);
        this.add(view, BorderLayout.CENTER);
        this.add(control, BorderLayout.SOUTH);
    }
}

class Control extends JPanel {

    private Model model;
    private View view;
    private JButton reset = new JButton("Reset");

    public Control(Model model, View view) {
        this.model = model;
        this.view = view;
        this.add(reset);
        reset.addActionListener(new ButtonHandler());
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if ("Reset".equals(cmd)) {
                model.reset();
            }
        }
    }
}

class View extends JPanel {

    private static final String s = "Click a button.";
    private Model model;
    private ColorIcon icon = new ColorIcon(80, Color.gray);
    private JLabel label = new JLabel(s, icon, JLabel.CENTER);

    public View(Model model) {
        super(new BorderLayout());
        this.model = model;
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);
        this.add(label, BorderLayout.CENTER);
        this.add(genButtonPanel(), BorderLayout.SOUTH);
        model.addObserver(new ModelObserver());
    }

    private JPanel genButtonPanel() {
        JPanel panel = new JPanel();
        for (Piece p : Piece.values()) {
            PieceButton pb = new PieceButton(p);
            pb.addActionListener(new ButtonHandler());
            panel.add(pb);
        }
        return panel;
    }

    private class ModelObserver implements Observer {

        @Override
        public void update(Observable o, Object arg) {
            if (arg == null) {
                label.setText(s);
                icon.color = Color.gray;
            } else {
                if ((Boolean) arg) {
                    label.setText("Win!");
                } else {
                    label.setText("Keep trying.");
                }
            }
        }
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            PieceButton pb = (PieceButton) e.getSource();
            icon.color = pb.piece.color;
            label.repaint();
            model.check(pb.piece);
        }
    }

    private static class PieceButton extends JButton {

        Piece piece;

        public PieceButton(Piece piece) {
            this.piece = piece;
            this.setIcon(new ColorIcon(16, piece.color));
        }
    }
}

enum Piece {
    RED(Color.RED),
    GREEN(Color.GREEN),
    BLUE(Color.BLUE),
    YELLOW(Color.YELLOW),
    MAGENTA(Color.MAGENTA),
    CYAN(Color.CYAN);

    public final Color color;

    private Piece(Color color) {
        this.color = color;
    }
}

class Model extends Observable {
    private Piece target;

    public void reset() {
        target = Piece.values()[new Random().nextInt(Piece.values().length)];
        setChanged();
        notifyObservers(null);
    }

    public void check(Piece guess) {
        boolean result = target == guess;
        setChanged();
        notifyObservers(result);
    }
}

class ColorIcon implements Icon {

    private int size;
    public Color color;

    public ColorIcon(int size, Color color) {
        this.size = size;
        this.color = color;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.fillRect(x, y, size, size);
        g2d.dispose();
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    @Override
    public int getIconHeight() {
        return size;
    }
}