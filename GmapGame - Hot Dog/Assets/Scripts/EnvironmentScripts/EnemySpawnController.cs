using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemySpawnController : MonoBehaviour {

    public GameObject EnemyToSpawn;

    private GameObject spawnedEnemy;

	// Use this for initialization
	void Start () {
        spawnedEnemy.SetActive(false);
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    public void SpawnEnemy()
    {
       if (spawnedEnemy.activeSelf == false)
       {
           spawnedEnemy.SetActive(true);
       }
    }
}
