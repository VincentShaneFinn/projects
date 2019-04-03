using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerVoiceLines : MonoBehaviour {

    public AudioClip AudioClip1;
    public AudioClip AudioClip2;
    public AudioClip AudioClip3;
    public AudioClip AudioClip4;
    public AudioSource AudioSource;

    public bool isBoss;

    private float countdown;
    public float cooldown;
    // Use this for initialization
    void Start () {
        countdown = cooldown;
        if (isBoss)
        {
            countdown = 1;
        }
    }
	
	// Update is called once per frame
	void Update () {
        if (countdown <= 0)
        {
            int pickVoice = Random.Range(0, 3);
            if (pickVoice == 0)
            {
                AudioSource.clip = AudioClip1;
                AudioSource.time = 0f;
            }
            else if (pickVoice == 1)
            {
                AudioSource.clip = AudioClip2;
                AudioSource.time = 0f;
            }
            else if (pickVoice == 2)
            {
                if (GameObject.FindGameObjectWithTag("Boss") != null)
                {
                    print("test");
                    AudioSource.clip = AudioClip3;
                    AudioSource.time = 0f;
                }
                else
                {
                    AudioSource.clip = AudioClip4;
                    AudioSource.time = 0f;
                }
            }
            AudioSource.Play();
            countdown = cooldown;
        }
        else
        {
            countdown -= Time.deltaTime;
        }
	}
}
