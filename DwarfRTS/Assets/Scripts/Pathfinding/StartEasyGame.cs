using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class StartEasyGame : MonoBehaviour {

    public Pathfinder ai;
    public GameObject buttons;
    public GameObject gold;

	// Use this for initialization
	void Start () {
        Time.timeScale = 0;
	}

    public void EasyGame()
    {
        ai.Astar = false;
        ai.Fast = false;
        Time.timeScale = 1;
        buttons.SetActive(false);
        gold.SetActive(true);
    }

    public void MediumGame()
    {
        ai.Astar = true;
        ai.Fast = false;
        Time.timeScale = 1;
        buttons.SetActive(false);
        gold.SetActive(true);
    }

    public void HardGame()
    {
        ai.Astar = true;
        ai.Fast = true;
        Time.timeScale = 1;
        buttons.SetActive(false);
        gold.SetActive(true);
    }

}
