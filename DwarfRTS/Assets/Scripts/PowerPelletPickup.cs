using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PowerPelletPickup : MonoBehaviour {

    public Text playerScore;
    public Text AIScore;
    public bool AIGold;
    public Pathfinder AIPathfinder;
    public LayerMask lm;
    public GameObject NextGoal;
    public bool FirstGold;

    // Use this for initialization
    void OnEnable () {
        if (AIGold && !FirstGold)
        {
            Transform goalNode = GetClosestNode();
            Transform aiNode = GetAINode();
            AIPathfinder.PathToTarget(aiNode.GetComponent<Node>(), goalNode.GetComponent<Node>());
        }
	}

    void OnTriggerEnter2D(Collider2D col)
    {
        try
        {
            NextGoal.SetActive(true);
        }
        catch(Exception e) { }
        if (col.gameObject.tag == "Player")
        {
            //Enable Power Mode
            //player.powerUp();
            switch (playerScore.text)
            {
                case "Player Gold: 0 / 3":
                    playerScore.text = "Player Gold: 1 / 3";
                    break;
                case "Player Gold: 1 / 3":
                    playerScore.text = "Player Gold: 2 / 3";
                    break;
                case "Player Gold: 2 / 3":
                    playerScore.text = "You Win";
                    Time.timeScale = 0;
                    break;
            }
            Destroy(gameObject);
        }
        else if(col.gameObject.tag == "Enemy")
        {
            switch (AIScore.text)
            {
                case "AI Gold: 0 / 3":
                    AIScore.text = "AI Gold: 1 / 3";
                    break;
                case "AI Gold: 1 / 3":
                    AIScore.text = "AI Gold: 2 / 3";
                    break;
                case "AI Gold: 2 / 3":
                    AIScore.text = "AI Wins";
                    Time.timeScale = 0;
                    break;
            }
            Destroy(gameObject);
        }
    }

    Transform GetClosestNode()
    {
        Collider2D hitCollider = Physics2D.OverlapCircle(transform.position, .5f,lm);
        print(hitCollider.gameObject);
        return hitCollider.transform;
    }

    Transform GetAINode()
    {
        Collider2D hitCollider = Physics2D.OverlapCircle(AIPathfinder.transform.position, .5f,lm);
        print(hitCollider.gameObject);
        return hitCollider.transform;
    }
}
