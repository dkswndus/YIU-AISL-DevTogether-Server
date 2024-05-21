package yiu.aisl.devTogether.service;

import yiu.aisl.devTogether.domain.Board;
import yiu.aisl.devTogether.domain.Comment;
import yiu.aisl.devTogether.domain.Scrap;
import yiu.aisl.devTogether.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yiu.aisl.devTogether.domain.state.GenderCategory;
import yiu.aisl.devTogether.domain.state.RoleCategory;
import yiu.aisl.devTogether.dto.*;
import yiu.aisl.devTogether.exception.CustomException;
import yiu.aisl.devTogether.exception.ErrorCode;
import yiu.aisl.devTogether.repository.BoardRepository;
import yiu.aisl.devTogether.repository.CommentRepository;
import yiu.aisl.devTogether.repository.ScrapRepository;
import yiu.aisl.devTogether.repository.UserRepository;
import yiu.aisl.devTogether.config.CustomUserDetails;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ScrapRepository scrapRepository;
    private final CommentRepository commentRepository;


    // [API]  내 정보 조회
    public Object getMyProfile(CustomUserDetails userDetails) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDetails.getUser().getEmail()).orElseThrow(
                () -> new CustomException(ErrorCode.NO_AUTH)
        ));
        return MyProfileResponseDto.builder()
                .email(user.get().getEmail())
                .name(user.get().getName())
                .nickname(user.get().getNickname())
                .role(user.get().getRole().ordinal()) //
                .gender(user.get().getGender().ordinal()) //
                .age(user.get().getAge())
                .location1(user.get().getLocation1())
                .location2(user.get().getLocation2())
                .location3(user.get().getLocation3())
                .subject1(user.get().getSubject1())
                .subject2(user.get().getSubject2())
                .subject3(user.get().getSubject3())
                .subject4(user.get().getSubject4())
                .subject5(user.get().getSubject5())
                .method(user.get().getMethod())
                .fee(user.get().getFee())
                .build();
    }

    // [API] 내 정보 수정
    public Boolean updateProfile(CustomUserDetails userDetails, MyProfileRequestDto dto) {
        User user = userRepository.findByEmail(userDetails.getUser().getEmail()).orElseThrow(()->
                new CustomException(ErrorCode.NO_AUTH)); // 권한 오류

        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setNickname(dto.getNickname());
        user.setRole(RoleCategory.values()[dto.getRole()]);
        user.setGender(GenderCategory.values()[dto.getGender()]);
        user.setAge(dto.getAge());
        user.setLocation1(dto.getLocation1());
        user.setLocation2(dto.getLocation2());
        user.setLocation3(dto.getLocation3());
        user.setSubject1(dto.getSubject1());
        user.setSubject2(dto.getSubject2());
        user.setSubject3(dto.getSubject3());
        user.setSubject4(dto.getSubject4());
        user.setSubject5(dto.getSubject5());
        user.setMethod(dto.getMethod());
        user.setFee(dto.getFee());
        return true;
    }

    // [API] 내가 작성한 댓글 조회
    public Object getMyComment(CustomUserDetails userDetails) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDetails.getUser().getEmail()).orElseThrow(
                () -> new CustomException(ErrorCode.NO_AUTH)
        ));

        List<Comment> myComments = commentRepository.findByUser(user.get());
        return myComments.stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());
    }

    // [API] 내가 작성한 글 조회
    public Object getMyBoard(CustomUserDetails userDetails) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDetails.getUser().getEmail()).orElseThrow(
                () -> new CustomException(ErrorCode.NO_AUTH)
        ));

        List<Board> myBoards = boardRepository.findByUser(user.get());
        return myBoards.stream()
                .map(BoardDto::new)
                .collect(Collectors.toList());
    }

    // [API] 내가 스크랩한 글 조회
//    public Object getMyScrap(CustomUserDetails userDetails) {
//        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDetails.getUser().getEmail()).orElseThrow(
//                () -> new CustomException(ErrorCode.NO_AUTH)
//        ));
//
//        List<Scrap> myScraps = scrapRepository.findByUser(user.get());
//        return myScraps.stream()
//                .map(ScrapDto::new)
//                .collect(Collectors.toList());
//    }
//
//    // [API] 내 멘티 관리하기
//    public Object getMyMentee(CustomUserDetails userDetails) {
//        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDetails.getUser().getEmail()).orElseThrow(
//                () -> new CustomException(ErrorCode.NO_AUTH)
//        ));
//
//    }
//
//    // [API] 내 멘토 관리하기
//    public Object getMyMentor(CustomUserDetails userDetails) {
//        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDetails.getUser().getEmail()).orElseThrow(
//                () -> new CustomException(ErrorCode.NO_AUTH)
//        ));
//
//    }
//
//    // [API] 내 멘토 프로필 변경하기
//    public Boolean changeProfile(CustomUserDetails userDetails) {
//        Optional<User> user = userRepository.findByEmail(userDetails.getUser().getEmail());
//
//        return true;
//    }
}
