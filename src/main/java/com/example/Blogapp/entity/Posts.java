    package com.example.Blogapp.entity;

    import jakarta.persistence.*;

    import java.util.HashSet;
    import java.util.Set;

    @Entity
    @Table(name="Posts")
    public class Posts {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String title;
        private String content;
        private String description;

        @OneToMany(mappedBy = "post",
                cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
        private Set<Comment> comments = new HashSet<>();

        public Set<Comment> getComments() {
            return comments;
        }

        public void setComments(Set<Comment> comments) {
            this.comments = comments;
        }

        public Posts() {
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
