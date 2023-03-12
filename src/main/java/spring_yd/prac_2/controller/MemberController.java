package spring_yd.prac_2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring_yd.prac_2.controller.dto.MemberReqDto;
import spring_yd.prac_2.controller.dto.MemberResDto;
import spring_yd.prac_2.controller.dto.PostSimpleResDto;
import spring_yd.prac_2.controller.dto.mapper.SuccessResDto;
import spring_yd.prac_2.domain.Member;
import spring_yd.prac_2.domain.Team;
import spring_yd.prac_2.repository.TeamRepository;
import spring_yd.prac_2.service.MemberService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService service;
    private final TeamRepository teamRepository;

    //회원가입
    @PostMapping("/api/auth/signup")
    public SuccessResDto<MemberResDto> signup(
            @RequestBody MemberReqDto req
    ){
        Member newMember = Member.createMember(req.getName(), req.getType());
        //teamName이 있다면,
        if(req.getTeamName() != null || req.getTeamName() != ""){
            Team team = teamRepository.getTeamByName(req.getTeamName());
            newMember.assignTeam(team);
        }
        service.join(newMember);

        return new SuccessResDto<>(
                HttpStatus.OK.value(),
                "회원가입 성공",
                convertMemberResDto(newMember, newMember.getTeam().getName())
        );
    }


    //전체 회원조회
    @GetMapping("/api/members")
    public SuccessResDto<List<MemberResDto>> getAllMembers(){
        List<Member> all = service.findAllMembers();

        List<MemberResDto> collect = all.stream()
                .map(m -> convertMemberResDto(m, m.getTeam().getName()))
                .collect(Collectors.toList());

        return new SuccessResDto<>(
                HttpStatus.OK.value(),
                "전체 회원조회 성공",
                collect
        );
    }

    //id로 회원조회
    @GetMapping("/api/members/{id}")
    public SuccessResDto<MemberResDto> getMemberById(
            @PathVariable("id") Long id
    ){
        Member findMember = service.findMemberById(id);
        //lazy 처리 되어있는거 가져오기
        String teamName = findMember.getTeam().getName();

        return new SuccessResDto<>(
                HttpStatus.OK.value(),
                "id로 회원조회 성공",
                convertMemberResDto(findMember, teamName)
        );

    }

    //탈퇴
    @DeleteMapping("/api/members/{memberId}")
    public SuccessResDto removeMember(
            @PathVariable("memberId") Long memberId){
        service.deleteMember(memberId);

        return new SuccessResDto(
                HttpStatus.OK.value(),
                "회원 탈퇴 성공",
                null
        ); //아니 왜 안돼...?
    }

    //======공통 함수 추출======
    private static List<PostSimpleResDto> convertToPostSimpleResDtos(Member m) {
        List<PostSimpleResDto> postCollect = m.getPosts().stream()
                .map(p -> new PostSimpleResDto(p.getId(), p.getTitle(), p.getCreatedAt()))
                .collect(Collectors.toList());
        return postCollect;
    }
    private static MemberResDto convertMemberResDto(Member member, String teamName) {
        return new MemberResDto(
                member.getId(),
                member.getName(),
                member.getType(),
                teamName,
                member.getCreatedAt(),
                member.getUpdatedAt(),
                convertToPostSimpleResDtos(member)
        );
    }
}
