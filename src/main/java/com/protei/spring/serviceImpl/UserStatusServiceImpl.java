package com.protei.spring.serviceImpl;

import com.protei.spring.model.UserStatus;
import com.protei.spring.repository.UserStatusRepository;
import com.protei.spring.response.SetStatusResponse;
import com.protei.spring.service.UserService;
import com.protei.spring.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.protei.spring.model.UserStatus.Status.AWAY;
import static com.protei.spring.model.UserStatus.Status.NO_STATUS;
import static com.protei.spring.model.UserStatus.Status.ONLINE;


public class UserStatusServiceImpl implements UserStatusService {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    UserStatusRepository userStatusRepository;

    @Autowired
    UserService userService;

    private static final Long executionDelayInMinutes = 5L;

    private UserStatusServiceImpl() {
    }

    public static UserStatusServiceImpl getUserStatusServiceImpl() {
        return new UserStatusServiceImpl();
    }

    @Transactional
    @Override
    public SetStatusResponse setStatus(UserStatus newStatus) {

        String oldUserStatus = userStatusRepository.findUserStatusById(newStatus.getId()).
                orElse(new UserStatus(newStatus.getId(), NO_STATUS)).
                getUserStatus().name();

        userStatusRepository.saveAndFlush(newStatus);

        if (newStatus.getUserStatus() == ONLINE) {
            Date initDelayDate = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(executionDelayInMinutes));

            threadPoolTaskScheduler.schedule(() -> {
                newStatus.setUserStatus(AWAY);
                userStatusRepository.saveAndFlush(newStatus);
            }, initDelayDate);
        }

        return new SetStatusResponse(
                newStatus.getId(),
                oldUserStatus,
                newStatus.getUserStatus().name());
    }

    @Override
    public UserStatus getUserStatusByUserId(Long userId) {
        return userStatusRepository.findUserStatusById(userId).orElse(new UserStatus(userId, NO_STATUS));
    }

}
