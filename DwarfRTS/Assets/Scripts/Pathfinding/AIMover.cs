using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AIMover : MonoBehaviour {

    public GameObject playerSprite;
    ////public Animator animator;
    public float moveSpeed;

    private Rigidbody2D myRigidbody;

    public LayerMask layerMask;

    public GameObject CurrentTarget;
    public List<GameObject> Path;
    public List<GameObject> NewPath;
    public bool NewPathSet = false;

    private void Start()
    {
        myRigidbody = GetComponent<Rigidbody2D>();
        //Ignore the collisions between layer 0 (default) and layer 8 (custom layer you set in Inspector window)
    }

    private void Update()
    {
        if (Path.Count > 0)
        {
            CurrentTarget = Path[0];
            if (transform.position != CurrentTarget.transform.position)
            {
                transform.position = Vector3.MoveTowards(transform.position, CurrentTarget.transform.position, Time.deltaTime * moveSpeed);
            }
            else
            {
                if (NewPathSet)
                {
                    Path = NewPath;
                    NewPathSet = false;
                }
                else
                {
                    Path.Remove(Path[0]);
                    //print(CurrentTarget);
                }
            }

            //playerSprite.transform.localEulerAngles = (transform.position - nextNode).normalized;
            Vector2 rotDir = (transform.position - CurrentTarget.transform.position).normalized;
            if (rotDir.magnitude > 0f)
            {
                float angle = Mathf.Atan2(-rotDir.y, -rotDir.x) * Mathf.Rad2Deg;
                playerSprite.transform.rotation = Quaternion.AngleAxis(angle, Vector3.forward);
            }
        }
        else if (NewPath.Count > 0)
        {
            Path = NewPath;
            NewPathSet = false;
        }
    }

    //could be called by the pathfinding method, but it make sure the starting node is whatever node the AI is currently going to
    public void SetNewPath(List<GameObject> np)
    {
        NewPath = np;
        NewPathSet = true;
    }
}
