package com.bemach.aep.pisentryweb.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.bemach.aep.pisentry.event.UdpEventSender;
import com.bemach.aep.pisentry.vos.Event;
import com.bemach.aep.pisentry.vos.EventType;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AlarmManagerTest {

	/**
	 * Mocking a DOC - verifying the indirect output from SUT.
	 * 
	 */
	@Test
	public void shouldDisarmWithCorrectEvent() {
		// Arrange
		AlarmManager alarmMgr = new AlarmManager();
		UdpEventSender mockEventSender = Mockito.mock(UdpEventSender.class);
		alarmMgr.setEventSender(mockEventSender);
		ArgumentCaptor<Event> argument = ArgumentCaptor.forClass(Event.class);
		Event expected = new Event(AlarmManager.class.toString(), EventType.DISARM, "NOOP");

		// Act
		alarmMgr.disarm();

		// Assert
		Mockito.verify(mockEventSender).send(argument.capture());
		Event actual = argument.getValue();
		assertThat(actual, equalTo(expected));
	}

}
