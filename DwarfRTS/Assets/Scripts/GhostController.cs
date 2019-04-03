using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GhostController : MonoBehaviour
{
    // Enums allow us to create small data containers that can only have a finite number of values.
    // They're great for state machines (like we're using here) or for unchanging values like compass directions (N, E, S, W)
    public enum EnemyState { Wander, Chase, Attack, Die };

    // Reference to animator component
    //public Animator animator;

    // Which layer (From Tags & Layers) an object has to be on for the scorpion to try to attack it
    public LayerMask attackTargetLayer;

    //-- Values to determine scorpion's behavior --
    public float wanderSpeed;
    public float chaseSpeed;
    public float rotationSpeed;

    public int attackDamage;

    public float sightDistance;
    public float attackDistance;

    public float minWanderTime;
    public float maxWanderTime;
    public float attackCooldown;
    //----------------------------------------------

    // What state the scorpion is currently in. There can only be one
    public EnemyState currentState;
    // Timer keeping track of how long it's been wandering
    private float currentWanderTime;
    // Timer keeping track of time since last attack
    private float cooldownTimer;
    // Direction facing
    private Vector2 currentDirection;
    // Target destination to move towards
    private GameObject currentTarget;
    // Reference to Rigidbody2D component
    private Rigidbody2D myRigidbody2D;
    // Boolean for waiting for animation event
    private bool struck = false;

    private void Start()
    {
        myRigidbody2D = GetComponent<Rigidbody2D>();

        // By default begin WANDER BEHAVIOR
        StartCoroutine(Wander());
    }

    private void Update()
    {
        // Keep track of timers
        currentWanderTime -= Time.deltaTime;
        cooldownTimer -= Time.deltaTime;
    }

    // WANDER BEHAVIOR. IEnumerator is the type we use to define Coroutines, which are functions
    // that execute at programmer-defined intervals. Please see the official Unity tutorial on
    // Coroutines that I've linked to the class
    private IEnumerator Wander()
    {
        currentState = EnemyState.Wander;
        int RandomNumber = Random.Range(0, 4);
        if(RandomNumber == 0)
        {
            currentDirection = new Vector2(0, 1);
        }
        else if (RandomNumber == 1)
        {
            currentDirection = new Vector2(0,-1);
        }
        else if (RandomNumber == 2)
        {
            currentDirection = new Vector2(1,0);
        }
        else if (RandomNumber == 3)
        {
            currentDirection = new Vector2(-1,0);
        }
        currentWanderTime = Random.Range(minWanderTime, maxWanderTime);

        while (currentState == EnemyState.Wander)
        {
            // Calculate velocity and sprite rotation
            myRigidbody2D.velocity = currentDirection * wanderSpeed;
            //animator.transform.localEulerAngles = Vector3.forward * ((Mathf.Rad2Deg * Mathf.Atan2(currentDirection.y, currentDirection.x)) - 90f);

            // After wandering timer has expired, pick a new direction to walk and set a new timer
            if (currentWanderTime <= 0f)
            {
                RandomNumber = Random.Range(0, 4);
                if (RandomNumber == 0)
                {
                    currentDirection = new Vector2(0, 1);
                }
                else if (RandomNumber == 1)
                {
                    currentDirection = new Vector2(0, -1);
                }
                else if (RandomNumber == 2)
                {
                    currentDirection = new Vector2(1, 0);
                }
                else if (RandomNumber == 3)
                {
                    currentDirection = new Vector2(-1, 0);
                }
                currentWanderTime = Random.Range(minWanderTime, maxWanderTime);
            }

            // Shoot raycast forward. If it hits an attackable target, set the target and switch to CHASE BEHAVIOR
            RaycastHit2D hit = Physics2D.Raycast(transform.position, currentDirection, sightDistance, attackTargetLayer.value);
            if (hit.collider != null)
            {
                currentTarget = hit.collider.gameObject;
                currentDirection = (currentTarget.transform.position - transform.position).normalized;
                StartCoroutine(Chase());
            }

            // Wait until next frame to restart the while loop
            yield return null;
        }
    }

    void OnCollisionEnter2D(Collision2D collision)
    {
        if (currentDirection == new Vector2(0, 1))
        {
            currentDirection = new Vector2(0, -1);
        }
        else if (currentDirection == new Vector2(0, -1))
        {
            currentDirection = new Vector2(1, 0);
        }
        else if (currentDirection == new Vector2(1, 0))
        {
            currentDirection = new Vector2(-1, 0);
        }
        else if (currentDirection == new Vector2(-1, 0))
        {
            currentDirection = new Vector2(0, 1);
        }
    }

    // CHASE BEHAVIOR
    private IEnumerator Chase()
    {
        currentState = EnemyState.Chase;

        while (currentState == EnemyState.Chase)
        {
            // Determine direction of the target
            Vector2 targetDirection = (currentTarget.transform.position - transform.position).normalized;
            // Pick direction to face this frame based on a smooth rotate-over-time value
            currentDirection = Vector3.RotateTowards(currentDirection.normalized, targetDirection, rotationSpeed * Mathf.Deg2Rad * Time.deltaTime, 0f).normalized;
            // Set velocity based on current direction
            myRigidbody2D.velocity = currentDirection * chaseSpeed;
            // Actually rotate the sprite based in local space
            //animator.transform.localEulerAngles = Vector3.forward * Mathf.Atan2(currentDirection.y, currentDirection.x);
            //animator.transform.localEulerAngles = Vector3.forward * ((Mathf.Rad2Deg * Mathf.Atan2(currentDirection.y, currentDirection.x)) - 90f);
            // Calculate updated distance to target
            float targetDistance = Vector3.Distance(currentTarget.transform.position, transform.position);

            // If target is within attack distance, begin ATTACK BEHAVIOR.
            if (targetDistance <= attackDistance)
            {
                StartCoroutine(Attack());
            }
            // If target is lost via line of sight, return to WANDER BEHAVIOR
            else if (targetDistance > sightDistance || Vector2.Angle(currentDirection, targetDirection) > 30f)
            {
                StartCoroutine(Wander());
            }

            // Wait until next frame to restart the while loop
            yield return null;
        }
    }

    // ATTACK BEHAVIOR
    private IEnumerator Attack()
    {
        currentState = EnemyState.Attack;
        myRigidbody2D.velocity = Vector2.zero;

        while (currentState == EnemyState.Attack)
        {
            // Play the attack animation
            //animator.SetTrigger("Attack");

            // Wait for it to reach the strike animation event
            while (!struck)
            {
                yield return null;
            }

            // After the animation event fires, shoot a raycast to determine if it hit the player
            //RaycastHit2D hit = Physics2D.Raycast(transform.position, currentDirection, attackDistance, attackTargetLayer.value);
            //if (hit.collider != null)
            //{
            //    // If the target is a player, inflict damage
            //    if (hit.collider.gameObject.GetComponent<PlayerHealth>() != null)
            //    {
            //        hit.collider.gameObject.GetComponent<PlayerHealth>().GetHit(attackDamage);
            //    }
            //}
            // Reset struck for future use, reset attack cooldown
            struck = false;
            cooldownTimer = attackCooldown;

            // Wait for cooldown timer to reach 0 before restarting the loop
            while (cooldownTimer > 0)
            {
                // If during the cooldown the player moves too far away to be struck again, switch to CHASE BEHAVIOR
                if (Vector3.Distance(currentTarget.transform.position, transform.position) > attackDistance)
                {
                    StartCoroutine(Chase());
                }
                yield return null;
            }

            // Go back to top of the while loop next frame
            yield return null;
        }
    }

    // Function is called externally using animation event
    public void Strike()
    {
        struck = true;
    }

    //// Function is called externally from scorpion's health script
    //public void DieNow()
    //{
    //    StartCoroutine(Die());
    //}

    // DEATH BEHAVIOR
    //private IEnumerator Die()
    //{
    //    currentState = EnemyState.Die;

    //    // Start death animation
    //    //animator.SetTrigger("Death");
    //    // Set velocity to zero and disable Collider component
    //    myRigidbody2D.velocity = Vector2.zero;
    //    GetComponent<Collider2D>().enabled = false;

    //    //// Wait...
    //    //while (true)
    //    //{
    //    //    // ...until the sprite gets set to null by the death animation, then destroy the scorpion gameObject.
    //    //    if (animator.GetComponent<SpriteRenderer>().sprite == null)
    //    //    {
    //    //        Destroy(gameObject);
    //    //    }
    //    //    yield return null;
    //    //}
    //}
}
