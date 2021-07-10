package chien.learn.PPMT.web;


import chien.learn.PPMT.domain.Project;
import chien.learn.PPMT.services.MapValidationService;
import chien.learn.PPMT.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project/")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapValidationService mapValidationService;

    @PostMapping("add")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }

    @GetMapping("find/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
        Project project = projectService.findProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("all")
    public Iterable<Project> getAllProjects() {
        return projectService.findAllProjects();
    }

    @PostMapping("delete/{projectId}")
    public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String projectId) {
        projectService.deleteProjectByIdentifier(projectId);
        return new ResponseEntity<String>("Đã xóa dự án '" + projectId + "'", HttpStatus.OK);
    }

}
