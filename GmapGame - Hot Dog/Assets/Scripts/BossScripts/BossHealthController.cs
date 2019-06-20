using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class BossHealthController : MonoBehaviour {

    public float health;
    private float currentHealth;
    public bool isInvulnerable;

    public Slider BossHealthBar;

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
            SceneManager.LoadScene(8);
            Instantiate(enemySmoke, transform.position, transform.rotation);
            BossHealthBar.gameObject.SetActive(false);
            Destroy(gameObject);

        }


        BossHealthBar.value = currentHealth / health;

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
