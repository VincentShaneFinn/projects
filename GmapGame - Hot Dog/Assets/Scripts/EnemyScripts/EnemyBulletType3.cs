using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyBulletType3 : MonoBehaviour {

    public float speed;

    private bool crissCrossLeft = false;
    private bool crissCrossRight = false;
    private float ccTurnSwapTime = 2.8f;
    private float ccTurnSwapCount;

    public int bulletDamage;
    // Use this for initialization
    void OnEnable()
    {
        ccTurnSwapCount = ccTurnSwapTime;
        crissCrossLeft = false;
        crissCrossRight = false;
    }

    public void ActivateCrissCrossLeft()
    {
        crissCrossLeft = true;
    }

    public void ActivateCrissCrossRight()
    {
        crissCrossRight = true;
    }


    // Update is called once per frame
    void Update()
    {
        transform.Translate(Vector3.forward * speed * Time.deltaTime); //deltatime is for update framerate variances
    }

    private void FixedUpdate()
    {
        if (crissCrossRight)
        {
            Quaternion rot = transform.rotation;
            Vector3 temp = rot.eulerAngles;
            if (ccTurnSwapCount < ccTurnSwapTime / 2)
            {
                temp = new Vector3(temp.x, temp.y - 2, temp.z);
            }
            else
            {
                temp = new Vector3(temp.x, temp.y + 2, temp.z);
            }
            if (ccTurnSwapCount <= 0)
            {
                ccTurnSwapCount = ccTurnSwapTime;
            }
            else
            {
                ccTurnSwapCount -= Time.deltaTime;
            }
            rot = Quaternion.Euler(temp);
            transform.rotation = rot;
        }
        else if (crissCrossLeft)
        {
            Quaternion rot = transform.rotation;
            Vector3 temp = rot.eulerAngles;
            if (ccTurnSwapCount < ccTurnSwapTime / 2)
            {
                temp = new Vector3(temp.x, temp.y + 2, temp.z);
            }
            else
            {
                temp = new Vector3(temp.x, temp.y - 2, temp.z);
            }
            if (ccTurnSwapCount <= 0)
            {
                ccTurnSwapCount = ccTurnSwapTime;
            }
            else
            {
                ccTurnSwapCount -= Time.deltaTime;
            }
            rot = Quaternion.Euler(temp);
            transform.rotation = rot;
        }
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.tag == "Player")
        {
            other.gameObject.GetComponent<PlayerHealthController>().HurtPlayer(bulletDamage, gameObject);
            //Destroy(gameObject);
        }
        else if (other.gameObject.tag == "Wall")
        {
            gameObject.SetActive(false);
        }
        else if (other.gameObject.tag == "PlayerBullet")
        {
            other.gameObject.SetActive(false);
            //Destroy(gameObject);
        }
    }
}
