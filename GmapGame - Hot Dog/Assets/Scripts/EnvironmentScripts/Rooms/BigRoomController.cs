using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BigRoomController : MonoBehaviour
{
    public GameObject PowerUp;
    public List<EnemyHealthController> enemies;

    // Use this for initialization
    void Start()
    {

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
                    PowerUp.SetActive(true);
                }
            }
        }
    }
}
