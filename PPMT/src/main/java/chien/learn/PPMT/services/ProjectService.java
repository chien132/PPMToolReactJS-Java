package chien.learn.PPMT.services;

import chien.learn.PPMT.domain.Project;
import chien.learn.PPMT.exceptions.ProjectIdException;
import chien.learn.PPMT.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Mã dự án '" + project.getProjectIdentifier().toUpperCase() + "' đã tồn tại");
        }
    }

    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Mã dự án '" + projectId + "' không tồn tại");
        }
        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = findProjectByIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Mã dự án '" + projectId + "' không tồn tại");
        }
        projectRepository.delete(project);
    }
}
