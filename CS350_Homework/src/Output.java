import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.util.*;
import java.io.*;

public class Output implements Serializable {

	public Output(){
		
	}
	public void toConsole(String s){
		System.out.println(s);		
	}
	public void toVoice(String s){
        String voiceName = "kevin16";
        
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice voice = voiceManager.getVoice(voiceName);

        voice.allocate();
        voice.speak(s);
        voice.deallocate();
    }
}
