package org.eivindkopperud.dummyspringbackend.comments

import org.eivindkopperud.dummyspringbackend.projections.ContentProjection
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comments")
class CommentController(val commentService: CommentService) {

    @PutMapping("/{id}")
    fun updateComment(
            @RequestHeader("user-id") userId: String,
            @PathVariable id: Long,
            @RequestBody(required = false) body: ContentProjection
    ) {
        return commentService.update(userId, id, body.content)
    }

    @DeleteMapping("/{id}")
    fun deleteComment(
            @RequestHeader("user-id") userId: String,
            @PathVariable id: Long
    ) {
        return commentService.delete(userId, id)
    }

    @PutMapping("/{id}/likes")
    fun toggleLike(
            @RequestHeader("user-id") userId: String,
            @PathVariable id: Long) = commentService.toggleLike(userId, id)
}