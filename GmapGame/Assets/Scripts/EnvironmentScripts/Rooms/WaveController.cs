using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class WaveController : MonoBehaviour {

    public List<EnemyHealthController> enemies;
    public TestRoomController theRoom;

    // Use this for initialization
    void OnEnable()
    {
        foreach (EnemyHealthController enemy in enemies)
        {
            enemy.gameObject.GetComponent<EnemyController>().spawnAnimation();
        }
    }

    // Update is called once per frame
    void Update()
    {
        int enemiesRemaining = enemies.Count;
        foreach (EnemyHealthController enemy in enemies)
        {
            
            if (enemy == null)
            {
                enemiesRemaining--;
                if (enemiesRemaining <= 0)
                {
                    theRoom.waveCleared();
                    Destroy(gameObject);
                }
            }
        }
    }
}
