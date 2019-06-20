using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TestRoomController : MonoBehaviour {

    public List<GameObject> waves;
    public GameObject door;
    public GameObject PickUp;

    private int index = 0;

    // Use this for initialization
    void Start()
    {
    }

    public void spawnWave()
    {
        door.SetActive(true);
        waves[index].SetActive(true);
    }
    public void waveCleared()
    {
        index++;
        if (index >= waves.Count)
        {
            door.SetActive(false);
            PickUp.SetActive(true);
        }
        else
        {
            Invoke("spawnWave", 1.5f);
        }
    }


}
