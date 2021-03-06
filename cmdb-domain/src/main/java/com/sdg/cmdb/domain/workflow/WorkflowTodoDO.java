package com.sdg.cmdb.domain.workflow;

import com.alibaba.fastjson.JSON;
import com.sdg.cmdb.domain.auth.UserDO;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@Data
public class WorkflowTodoDO implements Serializable {
    private static final long serialVersionUID = -49987520988688704L;

    private long id;
    private long wfId;
    private String wfName;
    private long applyUserId;
    private String applyDisplayName;
    //public static final int TODO_PHASE_CREATE = 0;
    public static final int TODO_PHASE_APPlY = 0;
    //Approval
    //public static final int TODO_PHASE_QA_APPROVAL = 1;
    public static final int TODO_PHASE_CMO_APPROVAL = 1;
    public static final int TODO_PHASE_TL_APPROVAL = 2;
    public static final int TODO_PHASE_DL_APPROVAL = 3;
    //  Auditing
    public static final int TODO_PHASE_AUDITING = 4;

    public static final int TODO_PHASE_COMPLETE = 5;
    /**
     * 工单阶段（工单执行阶段）
     * 0:发起人状态   1:QA状态  2:团队领导状态  3:部门领导状态  4:审核操作状态  5:执行完成
     */
    private int todoPhase = 0;

    // 正常
    public static final int TODO_STATUS_NORMAL = 0;
    // 回退
    public static final int TODO_STATUS_BACK = 1;
    // 拒绝
    public static final int TODO_STATUS_REFUSE = 2;
    // 完成
    public static final int TODO_STATUS_COMPLETE = 3;
    // 错误
    public static final int TODO_STATUS_ERR = 4;

    /**
     * 工单状态（判断是否正常）
     * 0:正常 1:拒绝回退 2:拒绝 3:正常完成 4:错误
     */
    private int todoStatus = 0;
    private String content;
    private String notice; // 邮件通知中的工作流信息
    private String gmtApply; // 发起时间
    private String gmtCreate;
    private String gmtModify;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public WorkflowTodoDO(WorkflowDO workflowDO, UserDO applyUser) {
        this.wfId = workflowDO.getId();
        this.wfName = workflowDO.getWfName();
        this.applyUserId = applyUser.getId();
        if (StringUtils.isEmpty(applyUser.getDisplayName())) {
            this.applyDisplayName = applyUser.getUsername();
        } else {
            this.applyDisplayName = applyUser.getUsername() + '<' + applyUser.getDisplayName() + '>';
        }
        this.todoPhase = 0;
        this.todoStatus = 0;
    }

    public WorkflowTodoDO() { }

    public WorkflowTodoDO(WorkflowTodoVO workflowTodoVO) {
        this.id = workflowTodoVO.getId();
        this.todoPhase = workflowTodoVO.getTodoPhase();
        this.todoStatus = workflowTodoVO.getTodoStatus();
        this.content = workflowTodoVO.getContent();
        this.notice = workflowTodoVO.getNotice();
        this.gmtApply = workflowTodoVO.getGmtApply();
        this.content = workflowTodoVO.getContent();
    }

}
