using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MovingEnemySpawnController : MonoBehaviour {

    public GameObject EnemyToSpawn;

    public Transform[] waypoints;

    private GameObject spawnedEnemy;

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    public void SpawnEnemy()
    {
       if (spawnedEnemy == null)
       {
           spawnedEnemy = Instantiate(EnemyToSpawn, transform.position, transform.rotation);
           spawnedEnemy.GetComponent<EnemyMovementController>().Waypoints = waypoints;
       }
    }
}
