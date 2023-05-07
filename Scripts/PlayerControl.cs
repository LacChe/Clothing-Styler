using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerControl : MonoBehaviour
{

    private bool facingRight = true;
    private int speed = 3;

    private SpriteRenderer spriteRenderer;
    private Animator animator;
    private Rigidbody2D rb2d;

    // Start is called before the first frame update
    void Awake()
    {
        spriteRenderer = GetComponent<SpriteRenderer>();
        animator = GetComponent<Animator>();
        rb2d = GetComponent<Rigidbody2D>();
    }

    // Update is called once per frame
    void Update()
    {
        movement();
        fall();
        if (Input.GetKeyDown("space") && !animator.GetCurrentAnimatorStateInfo(0).IsName("Player_Punch"))
        {
            animator.Play("Player_Punch");
        }
    }

    private void fall()
    {
        Vector2 move = Vector2.zero;
        move.y = -0.1f;
        rb2d.position = rb2d.position + move * Time.deltaTime * speed;
    }

    private void movement()
    {
        Vector2 move = Vector2.zero;

        move.x = Input.GetAxis("Horizontal");

        if (move.x < -0.001) facingRight = false;
        if (move.x > 0.001) facingRight = true;

        spriteRenderer.flipX = !facingRight;

        animator.SetFloat("VelocityX", Mathf.Abs(move.x));

        rb2d.position = rb2d.position + move * Time.deltaTime * speed;
    }
}
