using UnityEngine;

public class PlayerBulletController : MonoBehaviour {

    public float speed;
    public float destroyCountdown;
    private float destroyInTminus;

    public int bulletDamage;
    public Vector3 impactNormal;

    public GameObject primarySplash;

    // Use this for initialization
    void OnEnable () {
        destroyInTminus = destroyCountdown;
    }
	
	// Update is called once per frame
	void Update () {
        destroyInTminus -= Time.deltaTime;
        if(destroyInTminus <= 0)
        {
            gameObject.SetActive(false);
        }
        transform.Translate(Vector3.forward * speed * Time.deltaTime); //deltatime is for update framerate variances
	}

    private void OnTriggerEnter(Collider other)
    {
        if(other.gameObject.tag == "Enemy")
        {
            other.gameObject.GetComponent<EnemyHealthController>().HurtEnemy(bulletDamage);
            Instantiate(primarySplash, transform.position, Quaternion.Inverse(transform.rotation));
            gameObject.SetActive(false);
        }
        else if(other.gameObject.tag == "Wall")
        {
            Instantiate(primarySplash, transform.position, Quaternion.Inverse(transform.rotation));
            gameObject.SetActive(false);
            
        }
        else if (other.gameObject.tag == "Boss")
        {
            other.gameObject.GetComponent<BossHealthController>().AlwaysHurtEnemy(bulletDamage);
            gameObject.SetActive(false);
        }
    }
}
