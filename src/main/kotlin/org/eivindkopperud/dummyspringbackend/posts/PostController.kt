package org.eivindkopperud.dummyspringbackend.posts

import org.eivindkopperud.dummyspringbackend.comments.CommentService
import org.eivindkopperud.dummyspringbackend.projections.ContentProjection
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/posts")
class PostController(
        val postService: PostService,
        val commentService: CommentService
        ) {

    @GetMapping
    fun list() = postService.list()

    @PostMapping
    fun create(
            @RequestHeader("user-id") userId: String,
            @RequestBody body: ContentProjection) = postService.create(userId, body.content)

    @PutMapping("/{postId}")
    fun update(
            @RequestHeader("user-id") userId: String,
            @PathVariable postId: Long,
            @RequestBody(required = false) body: ContentProjection) {
        postService.update(userId, postId, body.content)
    }

    @DeleteMapping("/{postId}")
    fun delete(
            @RequestHeader("user-id") userId: String,
            @PathVariable postId: Long) = postService.delete(userId, postId)

    @GetMapping("/{postId}/comments")
    fun listComments(@PathVariable postId: Long) = commentService.retrieve(postId)

    @PostMapping("/{postId}/comments")
    fun createComment(
            @RequestHeader("user-id") userId: String,
            @PathVariable postId: Long,
            @RequestBody body: ContentProjection) = commentService.create(userId, postId, body.content)

}