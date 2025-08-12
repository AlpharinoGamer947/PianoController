import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Transmitter;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    public static double screenWidth = 1700;
    public static double scale = 0.5;

    public static boolean debug = false;

    static ArrayList<Octave> octaves = new ArrayList<>();
    static int octaveCount = 0;

    static int firstKey = 36;

    public static void main(String[] args) {

        MidiDevice md = null;
        int midiDeviceIndex = 0;


        System.out.println("\nMIDI Devices:");
        for (MidiDevice.Info mdi : MidiSystem.getMidiDeviceInfo()) {
            System.out.println(midiDeviceIndex + ": " + mdi);
            midiDeviceIndex++;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Number of Octaves: ");
        octaveCount = scanner.nextInt();
//        octaveCount = 5;

        double octaveWidth = 555;

        if (octaveCount >= 5) scale = (double) screenWidth / (octaveCount * octaveWidth);

        for (int j = 0; j < octaveCount; j++) {
            octaves.add(new Octave((j + 1), 36 * (j + 1)));
        }

        int md_i = 5;

        try {
            md = MidiSystem.getMidiDevice(MidiSystem.getMidiDeviceInfo()[md_i]);
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }

        if (debug) System.out.println("\nMidi Device index " + md_i);
        System.out.println("MIDI Device: " + md.getDeviceInfo().getName());

        Transmitter transmitter = null;
        MidiInputReciever mir = new MidiInputReciever(md.getDeviceInfo().toString());


        try {

            transmitter = md.getTransmitter();
            transmitter.setReceiver(mir);

            Thread reciverThread = new Thread(mir);
            reciverThread.start();

            md.open();
            System.out.println("Midi Device open\n");

        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }

        OctaveFrame of = new OctaveFrame();
        of.setVisible(true);

    }


    public static class OctaveFrame extends JFrame {

        JLabel key_id = new JLabel("KeyId: ");
        JLabel game_name = new JLabel("Game: ");
        JLabel command = new JLabel("KeyBinding: ");

        int octaveWidth = (int) (550 * scale);

        public OctaveFrame() {
            setTitle("PianoController: Octave Test");
            setSize(1700, 550);
            setLayout(null);
            for (int i = 0; i < Program.octaves.size(); i++) {
                Octave octave = Program.octaves.get(i);
                add(octave);
                octave.setPreferredSize(new Dimension((int) (500 * scale), (int) (500 * scale)));
                octave.setBounds(octaveWidth * i, 0, octaveWidth, (int) (500 * scale));
                octave.setVisible(true);
                System.out.println("Octave " + octave.getOctaveId() + ": " + Program.octaves.get(i));
            }
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public void setGameName(String game_name) {
            this.game_name.setText("Game: " + game_name);
        }

        public void setKeyId(int key_id) {
            this.key_id.setText("KeyId: " + key_id);
        }

        public void setKeyBinding(String key) {
            command.setText("KeyBinding: " + key);
        }
    }
}