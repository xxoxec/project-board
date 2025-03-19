package com.project.board.repository;

import com.project.board.domain.ArticleComment;
import com.project.board.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment> {

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.content, root.createAt, root.createBy);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like '%${v}%', 대소문자 구분X
        bindings.bind(root.createAt).first(DateTimeExpression::eq);
        bindings.bind(root.createBy).first(StringExpression::containsIgnoreCase);
    }
}