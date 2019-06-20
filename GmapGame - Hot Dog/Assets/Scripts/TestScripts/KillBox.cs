using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class KillBox : MonoBehaviour {

    /*void OnEnable()
    {
        {
            Invoke("Destroy", 2f);
        }
    }

    void Destroy()
    {
        gameObject.SetActive(false);
    }

    void OnDisable()
    {
        CancelInvoke();
    }*/

    private void OnTriggerExit(Collider other)
    {
        Destroy(other.gameObject.transform.parent.gameObject);
    }
}
