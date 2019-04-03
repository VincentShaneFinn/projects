using System.Collections;
using System.Collections.Generic;
using UnityEngine.UI;
using UnityEngine;
using UnityEngine.SceneManagement;

public class PlayerHealthController : MonoBehaviour {

    public int health;
    private int currentHealth;
    private bool isInvulnerable;

    public bool alwaysInvuln;

    public float invulnTime;
    private float invulnCount;
    private bool wasHit;
    public Text healthCounter;

    public Sprite fullW;
    public Sprite halfW;
    public Sprite emptyW;

    public Image Image1;
    public Image Image2;
    public Image Image3;
    public Image Image4;
    public Image Image5;


    public GameObject body;
    public GameObject hat;
    public GameObject gun;
    public GameObject hair;
    public GameObject hoverboard;

    public Material fadeMat;
    private Material bodyMat;
    private Material hatMat;
    private Material gunMat;
    private Material hairMat;
    private Material hoverboardMat;

    public AudioClip AudioClip1;
    public AudioClip AudioClip2;
    public AudioSource AudioSource;
    private int AudioCounter;

    // Use this for initialization
    void Start()
    {
        AudioCounter = 0;
        isInvulnerable = false;
        currentHealth = health;
        invulnCount = invulnTime;
        healthCounter.text = currentHealth.ToString();

        bodyMat = body.GetComponent<MeshRenderer>().material;
        hatMat = hat.GetComponent<MeshRenderer>().material;
        gunMat = gun.GetComponent<MeshRenderer>().material;
        hairMat = hair.GetComponent<MeshRenderer>().material;
        hoverboardMat = hoverboard.GetComponent<MeshRenderer>().material;
        wasHit = false;

        Image1.sprite = fullW;
        Image2.sprite = fullW;
        Image3.sprite = fullW;
        Image4.sprite = fullW;
        Image5.sprite = fullW;
    }

    // Update is called once per frame
    void Update()
    {
        if (currentHealth <= 0)
        {
            SceneManager.LoadScene(4);
            Destroy(gameObject);
        }
        if (wasHit)
        {
            if(invulnCount <= 0)
            {
                becomeVulnerable();
                wasHit = false;
                invulnCount = invulnTime;
                body.GetComponent<MeshRenderer>().material = bodyMat;
                hat.GetComponent<MeshRenderer>().material = hatMat;
                gun.GetComponent<MeshRenderer>().material = gunMat;
                hair.GetComponent<MeshRenderer>().material = hairMat;
                hoverboard.GetComponent<MeshRenderer>().material = hoverboardMat;
            }
            else
            {
                invulnCount -= Time.deltaTime;
                body.GetComponent<MeshRenderer>().material = fadeMat;
                hat.GetComponent<MeshRenderer>().material = fadeMat;
                gun.GetComponent<MeshRenderer>().material = fadeMat;
                hair.GetComponent<MeshRenderer>().material = fadeMat;
                hoverboard.GetComponent<MeshRenderer>().material = fadeMat;
            }
        }
    }

    public void becomeInvulnerable()
    {
        isInvulnerable = true;
    }

    public void becomeVulnerable()
    {
        isInvulnerable = false;
    }

    public void healthPickedUp()
    {
        if (currentHealth < health - 2)
        {
            currentHealth+= 2;
        }
        else
        {
            currentHealth = health;
        }
        //healthCounter.text = currentHealth.ToString();
        setHealthBar();
    }

    public void setHealthBar()
    {
        switch (currentHealth)
        {
            case 10:
                Image1.sprite = fullW;
                Image2.sprite = fullW;
                Image3.sprite = fullW;
                Image4.sprite = fullW;
                Image5.sprite = fullW;
                break;
            case 9:
                Image1.sprite = fullW;
                Image2.sprite = fullW;
                Image3.sprite = fullW;
                Image4.sprite = fullW;
                Image5.sprite = halfW;
                break;
            case 8:
                Image1.sprite = fullW;
                Image2.sprite = fullW;
                Image3.sprite = fullW;
                Image4.sprite = fullW;
                Image5.sprite = emptyW;
                break;
            case 7:
                Image1.sprite = fullW;
                Image2.sprite = fullW;
                Image3.sprite = fullW;
                Image4.sprite = halfW;
                Image5.sprite = emptyW;
                break;
            case 6:
                Image1.sprite = fullW;
                Image2.sprite = fullW;
                Image3.sprite = fullW;
                Image4.sprite = emptyW;
                Image5.sprite = emptyW;
                break;
            case 5:
                Image1.sprite = fullW;
                Image2.sprite = fullW;
                Image3.sprite = halfW;
                Image4.sprite = emptyW;
                Image5.sprite = emptyW;
                break;
            case 4:
                Image1.sprite = fullW;
                Image2.sprite = fullW;
                Image3.sprite = emptyW;
                Image4.sprite = emptyW;
                Image5.sprite = emptyW;
                break;
            case 3:
                Image1.sprite = fullW;
                Image2.sprite = halfW;
                Image3.sprite = emptyW;
                Image4.sprite = emptyW;
                Image5.sprite = emptyW;
                break;
            case 2:
                Image1.sprite = fullW;
                Image2.sprite = emptyW;
                Image3.sprite = emptyW;
                Image4.sprite = emptyW;
                Image5.sprite = emptyW;
                break;
            case 1:
                Image1.sprite = halfW;
                Image2.sprite = emptyW;
                Image3.sprite = emptyW;
                Image4.sprite = emptyW;
                Image5.sprite = emptyW;
                break;
            default:
                Image1.sprite = emptyW;
                Image2.sprite = emptyW;
                Image3.sprite = emptyW;
                Image4.sprite = emptyW;
                Image5.sprite = emptyW;
                break;
        }
    }

    public bool HurtPlayer(int damage, GameObject g)
    {
        if (alwaysInvuln)
        {
            return false;
        }
        if (!isInvulnerable)
        {
            currentHealth -= damage;
            //healthCounter.text = currentHealth.ToString();
            setHealthBar();
            becomeInvulnerable();
            wasHit = true;
            if (AudioCounter < 2)
            {
                AudioSource.clip = AudioClip1;
                AudioSource.time = 2.5f;
                AudioCounter++;
            }
            else
            {
                AudioSource.clip = AudioClip2;
                AudioSource.time = .3f;
                AudioCounter = 0;
            }
            AudioSource.Play();
            if (g.tag == "EnemyBullet1" || g.tag == "EnemyBullet2" || g.tag == "EnemyBulle3")
                g.SetActive(false);
            return true;
        }
        return false;
    }

    private void OnCollisionEnter(Collision other)
    {
        if(other.gameObject.tag == "Enemy" || other.gameObject.tag == "Boss")
        {
            HurtPlayer(2, other.gameObject);
        }
    }
}
