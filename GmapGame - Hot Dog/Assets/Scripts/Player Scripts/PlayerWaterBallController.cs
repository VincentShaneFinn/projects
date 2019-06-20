using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerWaterBallController : MonoBehaviour {

    public float speed;
    public float destroyCountdown;
    public int absorbsToLevelUp;
    public float maxScale;
    public float minScale;
    private int currentLevel;

    public int bulletDamage1;
    public int bulletDamage2;
    public int bulletDamage3;
    // Use this for initialization
    void Start()
    {
        currentLevel = 0;
    }

    // Update is called once per frame
    void Update()
    {
        destroyCountdown -= Time.deltaTime;
        if (destroyCountdown <= 0)
        {
            Destroy(gameObject);
        }
        transform.Translate(Vector3.forward * speed * Time.deltaTime); //deltatime is for update framerate variances
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.tag == "Enemy")
        {
            other.gameObject.GetComponent<EnemyHealthController>().AlwaysHurtEnemy(bulletDamage1);
            Destroy(gameObject);
        }
        else if (other.gameObject.tag == "Wall")
        {
            Destroy(gameObject);
        }
        else if (other.gameObject.tag == "EnemyBullet1")
        {
            other.gameObject.SetActive(false);
            //Destroy(gameObject);
        }
        else if (other.gameObject.tag == "EnemyBullet2")
        {
            other.gameObject.SetActive(false);
            currentLevel += 1;
            if (currentLevel >= absorbsToLevelUp / 2)
            {
                bulletDamage1 = bulletDamage2;
            }
            if (currentLevel >= absorbsToLevelUp)
            {
                bulletDamage1 = bulletDamage3;
            }
            else
            {
                gameObject.transform.localScale += new Vector3((maxScale - minScale) / absorbsToLevelUp, (maxScale - minScale) / absorbsToLevelUp, (maxScale - minScale) / absorbsToLevelUp);
            }
            //Destroy(gameObject);
        }
        else if (other.gameObject.tag == "Boss")
        {
            other.gameObject.GetComponent<BossHealthController>().AlwaysHurtEnemy(bulletDamage1);
            Destroy(gameObject);
        }
    }
}
