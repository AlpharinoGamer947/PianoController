import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class MidiInputReciever implements Receiver, Runnable {


    public MidiInputReciever(String string) {
    }

    @Override
    public void send(MidiMessage message, long timeStamp) {
        if (message instanceof ShortMessage) {
            ShortMessage sm = (ShortMessage) message;
            int cmd = sm.getCommand();
            int key = sm.getData1();
            int velocity = sm.getData2();

            int octNum = (key - Program.firstKey) / 12;
            int octKeyNum = (key - Program.firstKey) % 12;

            Octave.Key oKey = null;
            if (key != 0 && velocity > 0) {
                System.out.println("--------------\nkey: " + key);
                System.out.println("octNum: " + octNum);
                System.out.println("octKeyNum: " + octKeyNum);

                Octave oct = Program.octaves.get(octNum);
                System.out.println("Octave Object: " + oct);
                System.out.println("Octave Key Object: " + oKey);
            }

            oKey = Program.octaves.get(octNum).getKey(octKeyNum);

            if (key != 0 && cmd == ShortMessage.NOTE_ON && velocity > 0) {
                oKey.keyPressed();
                System.out.println(String.format("%-35s", "key: " + String.format("%3d", key) + " > Binding: " + IsoCoreKeyBinding.press(key)));
            } else {
                IsoCoreKeyBinding.release(key);
                oKey.keyReleased();
            }
        }
    }


    @Override
    public void close() {

    }

    @Override
    public void run() {
    }
}
