package com.azimo.tool.jira.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by F1sherKK on 11/01/17.
 */
public class Issue {

    public Fields fields;

    public Issue() {
        fields = new Fields();
    }

    public static class Fields {

        public Project project;
        public String summary;
        public String description;
        public IssueType issuetype;
        public Assignee assignee;
        public Reporter reporter;
        public List<String> labels;
        public Priority priority;

        public Fields() {
            project = new Project();
            issuetype = new IssueType();
            assignee = new Assignee();
            priority = new Priority();
            reporter = new Reporter();
            labels = new ArrayList<>();
        }

        public static class Project {
            public String key;
        }

        public static class IssueType {
            public String name;
        }

        public static class Assignee {
            public String name;
        }

        public static class Reporter {
            public String name;
        }

        public static class Priority {
            public String name = "Minor";
        }
    }

    public void setProject(String projectName) {
        fields.project.key = projectName;
    }

    public void setIssueType(String issueType) {
        fields.issuetype.name = issueType;
    }

    public void setAssignee(String userName) {
        fields.assignee.name = userName;
    }

    public void setReporter(String name) {
        fields.reporter.name = name;
    }

    public void addLabels(String labels) {
        fields.labels.add(labels);
    }

    public void setPriority(String priority) {
        fields.priority.name = priority;
    }

    public void setSummary(String summary) {
        fields.summary = summary;
    }

    public void setDescription(String description) {
        fields.description = description;
    }
}
