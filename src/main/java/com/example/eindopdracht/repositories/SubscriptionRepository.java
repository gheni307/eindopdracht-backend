package com.example.eindopdracht.repositories;

import com.example.eindopdracht.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
