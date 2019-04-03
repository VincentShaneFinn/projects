using System.Collections;
using System.Collections.Generic;
using UnityEngine.UI;
using UnityEngine;

public class TextController : MonoBehaviour {

    private float startTime;
    public float startFadeIn;
    public float startFadeOut;
    private Text text;

	// Use this for initialization
	void Start () {
        text = GetComponent<Text>();
        text.color = new Color(text.color.r, text.color.g, text.color.b, 0);
        startTime = Time.time;
    }
	
	// Update is called once per frame
	void Update () {
		if (Time.time - startTime == startFadeIn)
        {
            StartCoroutine(FadeTextToFullAlpha(1f, GetComponent<Text>()));
        }
        if (Time.time - startTime == startFadeOut)
        {
            StartCoroutine(FadeTextToZeroAlpha(1f, GetComponent<Text>()));
        }
	}

    public IEnumerator FadeTextToZeroAlpha(float t, Text i)
    {
        i.color = new Color(i.color.r, i.color.g, i.color.b, 1);
        while (i.color.a > 0.0f)
        {
            i.color = new Color(i.color.r, i.color.g, i.color.b, i.color.a - (Time.deltaTime / t));
            yield return null;
        }
    }

    public IEnumerator FadeTextToFullAlpha(float t, Text i)
    {
        i.color = new Color(i.color.r, i.color.g, i.color.b, 0);
        while (i.color.a < 1.0f)
        {
            i.color = new Color(i.color.r, i.color.g, i.color.b, i.color.a + (Time.deltaTime / t));
            yield return null;
        }
    }
}
