package ksw.h_r_recruitment_management_system.service;

import ksw.h_r_recruitment_management_system.domain.InterviewRoom;
import ksw.h_r_recruitment_management_system.repository.InterviewRoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InterviewRoomService {

    public static final String NOT_FOUND = "The interview room with that id does not exist";

    @Autowired
    private InterviewRoomRepository interviewRoomRepo;

    public String saveInterviewRoom(InterviewRoom i) {
        interviewRoomRepo.save(i);
        return "Interview room saved successfully";
    }

    public List<InterviewRoom> getAllInterviewRooms() {
        return interviewRoomRepo.findAll();
    }

    public Optional<InterviewRoom> getInterviewRoomById(UUID id) {
        return interviewRoomRepo.findById(id);
    }

    public String updateInterviewRoom(UUID id, InterviewRoom updated) {
        Optional<InterviewRoom> existing = interviewRoomRepo.findById(id);
        if (existing.isEmpty()) {
            return NOT_FOUND;
        }
        InterviewRoom room = existing.get();
        room.setRoomNumber(updated.getRoomNumber());
        room.setName(updated.getName());
        interviewRoomRepo.save(room);
        return "Interview room updated successfully";
    }

    public String deleteInterviewRoom(UUID id) {
        if (!interviewRoomRepo.existsById(id)) {
            return NOT_FOUND;
        }
        interviewRoomRepo.deleteById(id);
        return "Interview room deleted successfully";
    }

    public List<InterviewRoom> getFreeInterviewRooms() {
        return interviewRoomRepo.findFreeInterviewRooms();
    }
}
