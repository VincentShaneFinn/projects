using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BossFirePatternController : MonoBehaviour {

    public float timeToSwitch;
    private float timeCount;

    public float pauseTime;
    private float pauseTimeCount;

    public int phase;
    private float maxHealth;

    private int test = 3;

	// Use this for initialization
	void Start () {
        timeCount = 0;
        pauseTimeCount = pauseTime;
        phase = 1;
        maxHealth = GetComponent<BossHealthController>().health;
    }
	
	// Update is called once per frame
	void Update () {
        if (timeCount <= 0)
        {
            if (!gameObject.GetComponent<BossController>().isFiring)
            {

                gameObject.GetComponents<BossBigWaveController>()[0].enabled = false;
                gameObject.GetComponents<BossBigWaveController>()[1].enabled = false;

                gameObject.GetComponents<BossCrissCrossController>()[0].enabled = false;
                gameObject.GetComponents<BossCrissCrossController>()[1].enabled = false;
                gameObject.GetComponents<BossCrissCrossController>()[2].enabled = false;

                gameObject.GetComponents<BossBallThrowController>()[0].enabled = false;
                gameObject.GetComponents<BossBallThrowController>()[1].enabled = false;

                gameObject.GetComponents<BossTripleAngledController>()[0].enabled = false;
                gameObject.GetComponents<BossTripleAngledController>()[1].enabled = false;

                gameObject.GetComponents<BossTripleLinesController>()[0].enabled = false;
                gameObject.GetComponents<BossTripleLinesController>()[1].enabled = false;
                gameObject.GetComponents<BossTripleLinesController>()[2].enabled = false;

                if (pauseTimeCount <= 0)
                {
                    pauseTimeCount = pauseTime;

                    //everything below runs once every n seconds until the current bullet pattern is done with
                    //this is where we would check and switch phases

                    if(gameObject.GetComponent<BossHealthController>().BossHealthBar.value < .33)
                    {
                        phase = 3;
                        gameObject.GetComponent<BossMovementController>().phase = 3;
                    }
                    else if(gameObject.GetComponent<BossHealthController>().BossHealthBar.value < .66){
                        phase = 2;
                        gameObject.GetComponent<BossMovementController>().phase = 2;
                    }

                    //test sequential moves for now
                    if (test > 3)
                    {
                        test = 0;
                    }
                    else
                    {
                        test++;
                    }

                    timeCount = timeToSwitch;
                    //pick a random bullet pattern

                    if (test == 0)
                    {
                        gameObject.GetComponents<BossTripleAngledController>()[0].enabled = true;
                        gameObject.GetComponents<BossTripleAngledController>()[1].enabled = true;

                        gameObject.GetComponents<BossTripleAngledController>()[0].EnemyLevel = phase;
                        gameObject.GetComponents<BossTripleAngledController>()[1].EnemyLevel = phase;
                    }
                    else if (test == 1)
                    {
                        gameObject.GetComponents<BossBigWaveController>()[0].enabled = true;
                        gameObject.GetComponents<BossBigWaveController>()[1].enabled = true;

                        gameObject.GetComponents<BossBigWaveController>()[0].EnemyLevel = phase;
                        gameObject.GetComponents<BossBigWaveController>()[1].EnemyLevel = phase;
                    }
                    else if (test == 2)
                    {
                        gameObject.GetComponents<BossBallThrowController>()[0].enabled = true;
                        gameObject.GetComponents<BossBallThrowController>()[1].enabled = true;

                        gameObject.GetComponents<BossBallThrowController>()[0].EnemyLevel = phase;
                        gameObject.GetComponents<BossBallThrowController>()[1].EnemyLevel = phase;
                    }
                    else if (test == 3)
                    {
                        gameObject.GetComponents<BossCrissCrossController>()[0].enabled = true;
                        gameObject.GetComponents<BossCrissCrossController>()[1].enabled = true;
                        gameObject.GetComponents<BossCrissCrossController>()[2].enabled = true;

                        gameObject.GetComponents<BossCrissCrossController>()[0].EnemyLevel = phase;
                        gameObject.GetComponents<BossCrissCrossController>()[1].EnemyLevel = phase;
                        gameObject.GetComponents<BossCrissCrossController>()[2].EnemyLevel = phase;

                    }
                    else if(test == 4)
                    {
                        gameObject.GetComponents<BossTripleLinesController>()[0].enabled = true;
                        gameObject.GetComponents<BossTripleLinesController>()[1].enabled = true;
                        gameObject.GetComponents<BossTripleLinesController>()[2].enabled = true;

                        gameObject.GetComponents<BossTripleLinesController>()[0].EnemyLevel = phase;
                        gameObject.GetComponents<BossTripleLinesController>()[1].EnemyLevel = phase;
                        gameObject.GetComponents<BossTripleLinesController>()[2].EnemyLevel = phase;
                    }
                }
                else
                {
                    //time paused between firing
                    pauseTimeCount -= Time.deltaTime;
                }
            }
        }
        else
        {
            //keep the current pattern live until time runs out
            timeCount -= Time.deltaTime;
            
        }
	}
}
