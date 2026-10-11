
package com.example.job_portal_management_api.model;

import java.util.List;

public record Candidate(
        Long id,
        String name,
        String email,
        List<String> skills,
        String jobTitle,
        String status
) {}
