package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


//repository(in jpa) == dao라고도 불리는 db layer접근자
public interface PostsRepository extends JpaRepository<Posts,Long> {
    //<Entity 클래스, pk타입>을 상속하면 기본적인 CRUD 메소드가 자동으로 생성

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
