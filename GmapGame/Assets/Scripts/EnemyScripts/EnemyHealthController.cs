using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyHealthController : MonoBehaviour {

    public int health;
    private int currentHealth;
    public bool isInvulnerable;

    public GameObject enemySmoke;

    // Use this for initialization
    void Start () {
        currentHealth = health;
        isInvulnerable = false;
	}

    public void MakeInvulnerable()
    {
        isInvulnerable = true;
    }

    public void MakeVulnerable()
    {
        isInvulnerable = false;
    }

    // Update is called once per frame
    void Update () {
		if(currentHealth <= 0)
        {
            Instantiate(enemySmoke, transform.position, transform.rotation);
            Destroy(gameObject);
        }
	}

    public void HurtEnemy(int damage)
    {
        if (!isInvulnerable)
        {
            currentHealth -= damage;
        }
    }

    public void AlwaysHurtEnemy(int damage)
    {

        currentHealth -= damage;

    }
}
