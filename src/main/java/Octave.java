import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Octave extends JPanel {
    private final int c_key_id;
    ArrayList<Key> keylist = new ArrayList<>();
    BlackKey cd = new BlackKey('C', 'D', 61);
    BlackKey de = new BlackKey('D', 'E', 63);
    BlackKey fg = new BlackKey('F', 'G', 66);
    BlackKey ga = new BlackKey('G', 'A', 68);
    BlackKey ab = new BlackKey('A', 'B', 70);
    private int octaveId = 0;
    WhiteKey c = new WhiteKey(octaveId, 'C', 60);
    WhiteKey d = new WhiteKey(octaveId, 'D', 62);
    WhiteKey e = new WhiteKey(octaveId, 'E', 64);
    WhiteKey f = new WhiteKey(octaveId, 'F', 65);
    WhiteKey g = new WhiteKey(octaveId, 'G', 67);
    WhiteKey a = new WhiteKey(octaveId, 'A', 69);
    WhiteKey b = new WhiteKey(octaveId, 'B', 71);

    public Octave(int groupId, int c_key_id) {

        this.octaveId = groupId;
        this.c_key_id = c_key_id;

        setSize(new Dimension((int) (500 * Program.scale), (int) (500 * Program.scale)));
        setBackground(Color.lightGray);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension((int) (600 * Program.scale), (int) (500 * Program.scale)));
        layeredPane.setBounds(new Rectangle(0, 0, (int) (600 * Program.scale), (int) (500 * Program.scale)));
        layeredPane.setVisible(true);

        // Setting the x, y of the Keys
        {
            c.setDisplacement((int) ((int) (75 * Program.scale) * 0.5), 0);
            cd.setDisplacement((int) ((int) (75 * Program.scale) * 1), 0);
            d.setDisplacement((int) ((int) (75 * Program.scale) * 1.5), 0);
            de.setDisplacement((int) ((int) (75 * Program.scale) * 2), 0);
            e.setDisplacement((int) ((int) (75 * Program.scale) * 2.5), 0);
            f.setDisplacement((int) ((int) (75 * Program.scale) * 3.5), 0);
            fg.setDisplacement((int) ((int) (75 * Program.scale) * 4), 0);
            g.setDisplacement((int) ((int) (75 * Program.scale) * 4.5), 0);
            ga.setDisplacement((int) ((int) (75 * Program.scale) * 5), 0);
            a.setDisplacement((int) ((int) (75 * Program.scale) * 5.5), 0);
            ab.setDisplacement((int) ((int) (75 * Program.scale) * 6), 0);
            b.setDisplacement((int) ((int) (75 * Program.scale) * 6.5), 0);
        }

        // Setting the groupId of WhiteKeys
        {
            c.setGroupId(octaveId);
            d.setGroupId(octaveId);
            e.setGroupId(octaveId);
            f.setGroupId(octaveId);
            g.setGroupId(octaveId);
            a.setGroupId(octaveId);
            b.setGroupId(octaveId);
        }

        // Adding Keys to keylist
        {
            keylist.add(c);
            keylist.add(cd);
            keylist.add(d);
            keylist.add(de);
            keylist.add(e);
            keylist.add(f);
            keylist.add(fg);
            keylist.add(g);
            keylist.add(ga);
            keylist.add(a);
            keylist.add(ab);
            keylist.add(b);
        }

        // Adding Keys to OctaveUI
        {
            layeredPane.add(c, JLayeredPane.DEFAULT_LAYER);
            layeredPane.add(d, JLayeredPane.DEFAULT_LAYER);
            layeredPane.add(e, JLayeredPane.DEFAULT_LAYER);
            layeredPane.add(f, JLayeredPane.DEFAULT_LAYER);
            layeredPane.add(g, JLayeredPane.DEFAULT_LAYER);
            layeredPane.add(a, JLayeredPane.DEFAULT_LAYER);
            layeredPane.add(b, JLayeredPane.DEFAULT_LAYER);

            layeredPane.add(cd, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(de, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(fg, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(ga, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(ab, JLayeredPane.PALETTE_LAYER);
        }

        add(layeredPane);

    }

    public int getOctaveId() {
        return octaveId;
    }

    public Key getKey(int key_id) {
        return keylist.get(key_id);
    }

    public class WhiteKey extends Key {
        private int groupId;
        private int letter;

        public WhiteKey(int groupId, char letter, int key_id) {
            super(key_id);
            this.letter = letter;
            this.groupId = groupId;
            setPreferredSize(new Dimension((int) (75 * Program.scale), (int) (375 * Program.scale)));
            setText(Character.toString(letter) + groupId);
            setBackground(Color.white);
            setForeground(Color.black);
            setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(Color.black, 2), // black outline
                            BorderFactory.createEmptyBorder(0, 0, 10, 0) // bottom padding
                    )
            );
//
        }

        public void setDisplacement(int x, int y) {
            setBounds(x, y, (int) (75 * Program.scale), (int) (375 * Program.scale));
        }

        public void keyPressed() {
            setBackground(Color.decode("#ff7575"));
            repaint();
        }

        public void keyReleased() {
            setBackground(Color.white);
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            keyPressed();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            keyReleased();
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
            setText(Character.toString(letter) + groupId);
        }
    }

    public class Key extends JLabel implements MouseListener {


        private final int key_id;
        private final String name;

        public Key(String name) {
            this.name = name;
            key_id = -1;
        }

        @Override
        public String getName() {
            return name;
        }

        public Key(int key_id) {
            this.name = Integer.toString(key_id);
            this.key_id = key_id;

            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.BOTTOM);

            setText(key_id + "");

            setFont(new Font("Agency FB", Font.BOLD, (int) (40 * Program.scale)));

            setOpaque(true);

            revalidate();
            repaint();

            addMouseListener(this);
        }

        public void keyPressed() {
        }

        public void keyReleased() {
        }

        public int getKey_id() {
            return key_id;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    public class BlackKey extends Key {
        public BlackKey(char letter1, char letter2, int key_id) {
            super(key_id);
            setPreferredSize(new Dimension((int) (70 * Program.scale), (int) (250 * Program.scale)));
            setText("<html><span style='font-family: Agency FB, Arial Unicode MS, SansSerif;'>" + letter1 + "#" + "<br>" + letter2 + "♭</span></html>");
            setBackground(Color.black);
            setForeground(Color.white);
            setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(Color.black, 3), // black outline
                            BorderFactory.createEmptyBorder(0, 0, 10, 0) // bottom padding
                    )
            );
        }

        public void keyPressed() {
            setBackground(Color.decode("#ff9575"));
            setForeground(Color.black);
        }

        public void keyReleased() {
            setBackground(Color.black);
            setForeground(Color.white);
        }

        public void setDisplacement(int x, int y) {
            setBounds(x, y, (int) (70 * Program.scale), (int) (280 * Program.scale));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            keyPressed();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            keyReleased();
        }
    }
}
