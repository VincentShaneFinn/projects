using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class SkipOnEnter : MonoBehaviour {

    public void OnMouseClick(int sceneIndex)
    {
        SceneManager.LoadScene(sceneIndex);
    }
}
