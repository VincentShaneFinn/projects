using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GlobalPressurePlate : MonoBehaviour {

    public List<EnemySpawnController> stationarySpawnPoints;
    public List<MovingEnemySpawnController> movingSpawnPoints;

    public TestRoomController room;

    private bool buttonPressed = false;

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    void OnTriggerEnter(Collider collision)
    {
        if (!buttonPressed && collision.gameObject.tag == "Player")
        {
            buttonPressed = true;
            foreach(EnemySpawnController spawnPoint in stationarySpawnPoints)
            {
                spawnPoint.SpawnEnemy();
            }
            foreach (MovingEnemySpawnController spawnPoint in movingSpawnPoints)
            {
                spawnPoint.SpawnEnemy();
            }
            room.spawnWave();
        }

    }
}
