package com.springboot.pratice.specifications;

import com.springboot.pratice.entity.Student;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

public final class StudentSpecification {

    private StudentSpecification() {
    }

    public static Specification<Student> isNotDeleted() {

        return (root, query, cb) ->
                cb.isFalse(root.get("isDeleted"));
    }

    public static Specification<Student> search(String search) {

        return (root, query, cb) -> {

            if (search == null || search.isBlank()) {
                return cb.conjunction();
            }

            String keyword = search.trim();

            String escapedKeyword = keyword
                    .toLowerCase()
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_");

            String pattern = "%" + escapedKeyword + "%";

            List<Predicate> predicates = new ArrayList<>();

            // Search by roll number (Long)
            try {
                Long rollNumber = Long.parseLong(keyword);

                if (rollNumber > 0) {
                    predicates.add(
                            cb.equal(root.get("rollNumber"), rollNumber)
                    );
                }

            } catch (NumberFormatException ignored) {
                // Search term is not a valid Long.
            }

            // Search by String fields
            predicates.add(
                    cb.like(
                            cb.lower(root.get("firstName")),
                            pattern,
                            '!'
                    )
            );

            predicates.add(
                    cb.like(
                            cb.lower(root.get("lastName")),
                            pattern,
                            '!'
                    )
            );

            predicates.add(
                    cb.like(
                            cb.lower(root.get("studentContact")),
                            pattern,
                            '!'
                    )
            );

            predicates.add(
                    cb.like(
                            cb.lower(root.get("studentAddress")),
                            pattern,
                            '!'
                    )
            );

            return cb.or(predicates.toArray(new Predicate[0]));
        };
    }
}