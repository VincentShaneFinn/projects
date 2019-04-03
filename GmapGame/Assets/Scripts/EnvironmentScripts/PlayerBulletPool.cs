using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerBulletPool : MonoBehaviour {

    public List<GameObject> pooledObjects;
    public GameObject objectToPool;
    public int amountToPool;

    public bool shouldExpand = true;

    // Use this for initialization
    void Start () {
        pooledObjects = new List<GameObject>();
        for (int i = 0; i < amountToPool; i++)
        {
            GameObject obj = (GameObject)Instantiate(objectToPool);
            obj.SetActive(false);
            pooledObjects.Add(obj);
        }
    }
	
	// Update is called once per frame
	void Update () {
		
	}

    public static PlayerBulletPool SharedInstance;

    void Awake()
    {
        SharedInstance = this;
    }

    public GameObject GetPooledObject()
    {
        // go through the pool
        for (int i = 0; i < pooledObjects.Count; i++)
        {
            // if inactive, return it so we can use it
            if (!pooledObjects[i].activeInHierarchy)
            {
                return pooledObjects[i];
            }
        }
        // otherwise we ran out   
        if (shouldExpand)
        {
            GameObject obj = (GameObject)Instantiate(objectToPool);
            obj.SetActive(false);
            pooledObjects.Add(obj);
            return obj;
        }
        else
        {
            return null;
        }
    }
}
