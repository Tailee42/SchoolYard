package fr.isika.cda.beans;

import fr.isika.cda.entities.common.AcademicLevel;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.lesson.AsynchronousLesson;
import fr.isika.cda.entities.lesson.Unit;
import fr.isika.cda.entities.school.Member;
import fr.isika.cda.entities.student.LearningPath;
import fr.isika.cda.entities.teacher.Teacher;
import fr.isika.cda.repositories.LearningPathRepository;
import fr.isika.cda.repositories.MemberRepository;
import fr.isika.cda.repositories.UnitRepository;
import fr.isika.cda.utils.SessionUtils;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
public class ShowUnitsUserBean {


    private Unit unit = new Unit();
    private List<Unit> units = new ArrayList<>();

    @Inject
    private UnitRepository unitRepository;
    @Inject
    private LearningPathRepository learningPathRepository;
    @Inject
    private MemberRepository memberRepository;

    public List<Unit> allUnitsByTeacher() {
        List<Unit> unitsUserTeacher = new ArrayList<>();
        List<Member> members = memberRepository.getAllMembersForOneUser(SessionUtils.getConnectedUser().getId());
        for (Member member : members) {
            if (member instanceof Teacher) {
                unitsUserTeacher.addAll(unitRepository.getAllUnitsByTeacherId(member.getId()));
            }
        }

        return unitsUserTeacher;
    }

    public List<Unit> allUnitsByUserByStudent() {
        List<Unit> unitsList = new ArrayList<>();
        List<LearningPath> learningPathList = learningPathRepository
                .getLearningPathsByUserId(SessionUtils.getConnectedUser().getId());
        for (LearningPath learningPath : learningPathList) {
            if (learningPath.getActivity() instanceof AsynchronousLesson) {
                AsynchronousLesson asynchronousLesson = (AsynchronousLesson) learningPath.getActivity();
                unitsList.add(asynchronousLesson.getUnit());
            }
        }
        return unitsList;
    }

    public String goToUnitForStudent(Unit unit) {
        List<LearningPath> learningPathList = learningPathRepository
                .getLearningPathsByUserId(SessionUtils.getConnectedUser().getId());

        for (LearningPath learningPath : learningPathList) {
            if (learningPath.getActivity() instanceof AsynchronousLesson) {
                AsynchronousLesson asynchronousLesson = (AsynchronousLesson) learningPath.getActivity();
                if (asynchronousLesson.getUnit().getId().equals(unit.getId())) {
                    SessionUtils.setConnectedMember(learningPath.getStudent());
                    SessionUtils.setCurrentSchool(learningPath.getStudent().getSchool());
                    break;
                }
            }
        }
        return "/indexUnit.xhtml?faces-redirect=true&id=" + unit.getId();
    }

    public String goToUnitForTeacher(Unit unit) {
        SessionUtils.setConnectedMember(unit.getTeacher());
        SessionUtils.setCurrentSchool(unit.getTeacher().getSchool());
        return "/indexUnit.xhtml?faces-redirect=true&id=" + unit.getId();
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public Map<String, SubjectEnum> subjects() {
        return SubjectEnum.subjects;
    }

    public Map<String, AcademicLevel> levels() {
        return AcademicLevel.Academiclevels;
    }

}
