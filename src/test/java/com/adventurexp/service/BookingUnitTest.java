package com.adventurexp.service;

import com.adventurexp.repository.BookingRepo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookingUnitTest {

    @Mock
    private BookingRepo repo;

    @InjectMocks
    private BookingService service;







}
