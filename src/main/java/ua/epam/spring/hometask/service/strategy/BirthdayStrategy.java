package ua.epam.spring.hometask.service.strategy;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Vladimir on 01.11.2017.
 */
@Component
public class BirthdayStrategy implements DiscountStrategy {

    @Override
    public byte getPercentDiscount(User user, Event event, LocalDateTime dateTime, Set<Long> seats) {

        if (user.getBirthday() != null){
            LocalDate userBirthday = user.getBirthday();
            LocalDate date = dateTime.toLocalDate();
            date = LocalDate.of(userBirthday.getYear(), date.getMonth().getValue(), date.getDayOfMonth());

            if(userBirthday.compareTo(date.minusDays(5))>=0 && userBirthday.compareTo(date.plusDays(5))<=0){
                return 10;
            }
        }
        return 0;
    }
}
