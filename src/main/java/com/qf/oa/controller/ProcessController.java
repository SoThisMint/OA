package com.qf.oa.controller;

import com.github.pagehelper.PageInfo;
import com.qf.oa.common.SysResult;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/28 15:50
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;

    @RequestMapping("toAdd")
    public String toAdd() {
        return "process/process_add";
    }

    /**
     * 流程部署
     *
     * @param processName
     * @param processFile
     * @return
     * @throws IOException
     */
    @RequestMapping("/add")
    public String add(String processName, @RequestParam MultipartFile processFile) throws IOException {
        DeploymentBuilder builder = repositoryService.createDeployment();
        builder.name(processName)
                .addZipInputStream(new ZipInputStream(processFile.getInputStream())).deploy();
        return "process/process_add";
    }

    @RequestMapping("/searchWithConditions")
    public String queryTaskList(Model model) {
        List<Task> taskList = taskService.createTaskQuery().list();
        PageInfo<Task> pageInfo = new PageInfo<>(taskList);
        model.addAttribute("url", "process/searchWithConditions");
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("taskList", taskList);
        return "process/taskToCheck";
    }

    @RequestMapping("/checkTask")
    public void toCheckTask(String id, boolean flag) {
//        System.out.println("id: "+id);
//        System.out.println("flag: "+flag);
        List<Task> taskList = taskService.createTaskQuery().list();
        Map<String, Object> map = new HashMap<>();
        map.put("flag", flag);
        for (Task task : taskList) {
            if (id.equals(task.getId())) {
                taskService.complete(id, map);
            }
        }
    }
}
