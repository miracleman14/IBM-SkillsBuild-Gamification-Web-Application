package org.example.ibmskillsbuildapp.service;

import org.example.ibmskillsbuildapp.model.Course;
import org.example.ibmskillsbuildapp.model.LearningPath;
import org.example.ibmskillsbuildapp.repo.CourseRepository;
import org.example.ibmskillsbuildapp.repo.LearningPathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing {@link Course} entities. This service provides methods for common
 * operations such as checking if the repository is empty and creating courses.
 */
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LearningPathRepository learningPathRepository;

    /**
     * Checks if the course repository is empty.
     *
     * @return true if the course repository is empty, false otherwise.
     */
    public boolean isEmpty() {
        return courseRepository.count() == 0;
    }

    /**
     * Creates and saves new courses in the course repository. The courses are associated with
     * specific learning paths.
     */
    public void createCourses() {
        LearningPath path1 = learningPathRepository.findByPathName("Artificial intelligence");
        LearningPath path2 = learningPathRepository.findByPathName("Cloud computing");
        LearningPath path3 = learningPathRepository.findByPathName("Design thinking");

        Course course1 = new Course("AI Foundations: A Collaboration of ISTE and IBM",
            "There's no doubt about the importance of artificial intelligence to future generations! Your job will most certainly use some type of AI. In this digital learning offering, created by ISTE and IBM especially for high school students, you'll learn the foundational concepts behind AI systems, consider the ethical implications of AI, explore applications of AI tools, and more.",
            "https://students.yourlearning.ibm.com/activity/PLAN-B2125F145F0E");
        course1.setLearningPath(path1);
        Course course2 = new Course("Artificial Intelligence Fundamentals",
            "Does artificial intelligence (AI) spark your interest? Here’s your chance to visualize yourself in an AI career! You’ll explore AI’s history, then see how it can change the world. Along the way, you’ll deep dive into ways that AI makes predictions, understands language and images, and learns using circuits inspired by the human brain. After a hands-on simulation in which you build and test a machine learning model, you’ll finish with tips on how to find your own career in artificial intelligence.",
            "https://students.yourlearning.ibm.com/activity/PLAN-CC702B39D429");
        course2.setLearningPath(path1);
        Course course3 = new Course("Cloud Computing Fundamentals",
            "Computer and information technology (IT) jobs are in demand and the cloud market is growing. The cloud is driving technological innovation and serving as the foundation for business innovation. Are you ready for this and the future ahead? Learn the basics of cloud computing, service models, deployment models, software, and the many ways businesses benefit from cloud technology. Then, get practice working with cloud computing in a series of simulations to build and deploy a Docker container and create and review the security settings for an IBM Cloudant database. Finish by gathering tips and resources that can help you launch a great career in cloud computing.",
            "https://students.yourlearning.ibm.com/activity/PLAN-58FA14F64C9B");
        course3.setLearningPath(path2);
        Course course4 = new Course("Introduction to Cloud",
            "This course introduces you to the core concepts of cloud computing. You will gain the foundational knowledge required for understanding cloud computing from both business and practitioner perspectives. You will learn about the definition and essential characteristics of cloud computing, its history, emerging trends, and the business case for cloud computing. You also learn about the various cloud service models (IaaS, PaaS, SaaS) and deployment models (Public Cloud, Private Cloud, Hybrid Cloud) and the key components of a cloud architecture (Virtualization, VMs, Storage, Networking, Containers, Serverless).",
            "https://students.yourlearning.ibm.com/activity/PLAN-4EB23B51588C");
        course4.setLearningPath(path2);

        Course course5 = new Course("Enterprise Design Thinking Practitioner",
            "Enterprise Design Thinking is IBM's approach to applying design thinking at the speed and scale a modern enterprise demands. It helps teams not only form intent, but deliver outcomes; outcomes that advance the state of the art and improve the lives of the people they serve."
                + "\n"
                + "Whether you're just starting out on your design thinking journey or searching for resources to expand your expertise, Enterprise Design Thinking is the perfect place to start. Complete this course and receive your Practitioner badge through this online curriculum! You'll also get access to the tools you need to practice human-centered design every day.",
            "https://ptech.yourlearning.ibm.com/activity/URL-1CA5E380CA4E");
        course5.setLearningPath(path3);

        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);
        courseRepository.save(course4);
        courseRepository.save(course5);
    }
}