import React from 'react';

export const GithubIcon = (props) =>
  React.createElement(
    'svg',
    { viewBox: '0 0 24 24', fill: 'currentColor', width: 24, height: 24, ...props },
    React.createElement('path', {
      d: 'M12 0C5.37 0 0 5.37 0 12c0 5.31 3.435 9.795 8.205 11.385.6.105.825-.255.825-.57 0-.285-.015-1.23-.015-2.235-3.015.555-3.795-.735-4.035-1.41-.135-.345-.72-1.41-1.23-1.695-.42-.225-1.02-.78-.015-.795.945-.015 1.62.87 1.845 1.23 1.08 1.815 2.805 1.305 3.495.99.105-.78.42-1.305.765-1.605-2.67-.3-5.46-1.335-5.46-5.925 0-1.305.465-2.385 1.23-3.225-.12-.3-.54-1.53.12-3.18 0 0 1.005-.315 3.3 1.23.96-.27 1.98-.405 3-.405s2.04.135 3 .405c2.295-1.56 3.3-1.23 3.3-1.23.66 1.65.24 2.88.12 3.18.765.84 1.23 1.905 1.23 3.225 0 4.605-2.805 5.625-5.475 5.925.435.375.81 1.095.81 2.22 0 1.605-.015 2.895-.015 3.3 0 .315.225.69.825.57A12.02 12.02 0 0024 12c0-6.63-5.37-12-12-12z'
    })
  );

export const LinkedinIcon = (props) =>
  React.createElement(
    'svg',
    { viewBox: '0 0 24 24', fill: 'currentColor', width: 24, height: 24, ...props },
    React.createElement('path', {
      d: 'M19 3a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h14m-.5 15.5v-5.3a3.26 3.26 0 0 0-3.26-3.26c-.85 0-1.84.52-2.28 1.3v-1.11h-2.79v8.37h2.79v-4.93c0-.77.62-1.4 1.39-1.4a1.4 1.4 0 0 1 1.4 1.4v4.93h2.75M6.46 8.76a1.69 1.69 0 1 0 0-3.38 1.69 1.69 0 0 0 0 3.38m1.39 9.74v-8.37H5.07v8.37h2.78z'
    })
  );

export const InstagramIcon = (props) =>
  React.createElement(
    'svg',
    { viewBox: '0 0 24 24', fill: 'currentColor', width: 24, height: 24, ...props },
    React.createElement('path', {
      d: 'M12 2.163c3.204 0 3.584.012 4.85.07 3.252.148 4.771 1.691 4.919 4.919.058 1.265.069 1.645.069 4.849 0 3.205-.012 3.584-.069 4.849-.149 3.225-1.664 4.771-4.919 4.919-1.266.058-1.644.07-4.85.07-3.204 0-3.584-.012-4.849-.07-3.26-.149-4.771-1.699-4.919-4.92-.058-1.265-.07-1.644-.07-4.849 0-3.204.013-3.583.07-4.849.149-3.227 1.664-4.771 4.919-4.919 1.266-.057 1.645-.069 4.849-.069zm0-2.163c-3.259 0-3.667.014-4.947.072-4.358.2-6.78 2.618-6.98 6.98-.059 1.281-.073 1.689-.073 4.948 0 3.259.014 3.668.072 4.948.2 4.358 2.618 6.78 6.98 6.98 1.281.058 1.689.072 4.948.072 3.259 0 3.668-.014 4.948-.072 4.354-.2 6.782-2.618 6.979-6.98.059-1.28.073-1.689.073-4.948 0-3.259-.014-3.667-.072-4.947-.196-4.354-2.617-6.78-6.979-6.98-1.281-.059-1.69-.073-4.949-.073zm0 5.838c-3.403 0-6.162 2.759-6.162 6.162s2.759 6.163 6.162 6.163 6.162-2.759 6.162-6.163c0-3.403-2.759-6.162-6.162-6.162zm0 10.162c-2.209 0-4-1.79-4-4 0-2.209 1.791-4 4-4s4 1.791 4 4c0 2.21-1.791 4-4 4zm6.406-11.845c-.796 0-1.441.645-1.441 1.44s.645 1.44 1.441 1.44c.795 0 1.439-.645 1.439-1.44s-.644-1.44-1.439-1.44z'
    })
  );

export const socialIcons = {
  GitHub: GithubIcon,
  LinkedIn: LinkedinIcon,
  Instagram: InstagramIcon
};

export const personal = {
  name: 'Muhammad Daffa Husen',
  shortName: 'Daffa',

  roles: [
    'Computer Engineering Graduate',
    'Front-End Developer',
    'UI/UX Designer',
    'Machine Learning Enthusiast'
  ],

  description:
    'Computer Engineering graduate passionate about building thoughtful digital experiences through web development, UI/UX design, machine learning, and emerging technologies.',

  email: 'daffahusen10@gmail.com',
  location: 'Banda Aceh, Indonesia',

  heroImage: '/images/profile-hero.jpg',
  cv: '/cv/CV_Muhammad_Daffa_Husen.pdf',

  about: {
    intro:
      'I create digital experiences.',

    description:
      'I am Muhammad Daffa Husen, a Computer Engineering graduate from Universitas Syiah Kuala with interests in front-end development, UI/UX design, machine learning, artificial intelligence, IoT, and embedded systems. I enjoy transforming ideas into functional and visually engaging digital products while continuously exploring new technologies and better ways to solve real-world problems.',

    location: 'Banda Aceh, Indonesia',

    role:
      'Computer Engineering Graduate · Front-End Developer · UI/UX Designer · Machine Learning Enthusiast'
  }
};

export const socials = [
  {
    name: 'GitHub',
    url: 'https://github.com/dappahsn',
    icon: 'GitHub'
  },
  {
    name: 'LinkedIn',
    url: 'https://www.linkedin.com/in/muhammaddaffahusen/',
    icon: 'LinkedIn'
  },
  {
    name: 'Instagram',
    url: 'https://www.instagram.com/dappahsn',
    icon: 'Instagram'
  }
];

// ─── SKILLS ──────────────────────────────────────────────────────────────────
export const skills = [
  {
    id: 'development',
    title: 'Development',
    eyebrow: '01 / BUILD',
    items: [
      {
        name: 'Next.js',
        description: 'React production framework providing server-side rendering, App Router, full-stack API routes, and optimized performance.'
      },
      {
        name: 'React.js',
        description: 'JavaScript library for building modern and interactive user interfaces with a component-based architecture.'
      },
      {
        name: 'TypeScript',
        description: 'Typed superset of JavaScript providing static typing, robust interfaces, and scalable code maintainability.'
      },
      {
        name: 'JavaScript',
        description: 'Programming language used to build dynamic and interactive web experiences across front-end and back-end.'
      },
      {
        name: 'Tailwind CSS',
        description: 'Utility-first CSS framework for rapidly building responsive, modern, and pixel-perfect custom user interfaces.'
      },
      {
        name: 'HTML',
        description: 'Standard markup language that structures the content and layout of web pages.'
      },
      {
        name: 'CSS',
        description: 'Stylesheet language used to control the visual presentation and layout of web documents.'
      },
      {
        name: 'Bootstrap',
        description: 'Open-source CSS framework providing pre-built responsive components and a flexible grid system.'
      },
      {
        name: 'Vite',
        description: 'Next-generation front-end build tool offering fast development server and optimized production builds.'
      },
      {
        name: 'Responsive Web Design',
        description: 'Approach to web design that ensures interfaces look and function well across all screen sizes and devices.'
      }
    ]
  },

  {
    id: 'design',
    title: 'Design',
    eyebrow: '02 / CRAFT',
    items: [
      {
        name: 'Figma',
        description: 'Collaborative design tool for UI/UX design, wireframing, prototyping, and design handoff.'
      },
      {
        name: 'UI/UX Design',
        description: 'Practice of designing user interfaces and experiences focused on usability, accessibility, and visual quality.'
      },
      {
        name: 'Wireframing',
        description: 'Low-fidelity visual guide representing the skeletal framework of a digital interface or product.'
      },
      {
        name: 'Prototyping',
        description: 'Creating interactive mockups to simulate and test user flows before final development.'
      },
      {
        name: 'Design Systems',
        description: 'Collection of reusable components and guidelines that ensure visual consistency across products.'
      },
      {
        name: 'Responsive Design',
        description: 'Design methodology ensuring consistent user experience across different screen sizes and devices.'
      }
    ]
  },

  {
    id: 'machine-learning',
    title: 'Machine Learning',
    eyebrow: '03 / INTELLIGENCE',
    items: [
      {
        name: 'Python',
        description: 'High-level programming language widely used in data science, machine learning, and automation.'
      },
      {
        name: 'Machine Learning',
        description: 'Field of AI that enables systems to learn and improve from data without being explicitly programmed.'
      },
      {
        name: 'Natural Language Processing',
        description: 'Branch of AI focused on enabling machines to understand and process human language.'
      },
      {
        name: 'Sentiment Analysis',
        description: 'NLP technique used to classify text data as positive, negative, or neutral sentiment.'
      },
      {
        name: 'Transformers',
        description: 'Deep learning architecture that powers state-of-the-art NLP models using self-attention mechanisms.'
      },
      {
        name: 'IndoBERT',
        description: 'Indonesian pre-trained BERT model optimized for natural language processing tasks in Bahasa Indonesia.'
      },
      {
        name: 'IndoBERTweet',
        description: 'Indonesian BERT variant fine-tuned on Twitter/social media data for informal language NLP tasks.'
      },
      {
        name: 'Scikit-learn',
        description: 'Python machine learning library providing tools for classification, regression, and clustering algorithms.'
      }
    ]
  },

  {
    id: 'tools',
    title: 'Tools & Technologies',
    eyebrow: '04 / SYSTEMS',
    items: [
      {
        name: 'Zustand',
        description: 'Fast, lightweight, and scalable state management solution for modern React applications.'
      },
      {
        name: 'Git',
        description: 'Distributed version control system for tracking changes in source code during software development.'
      },
      {
        name: 'GitHub',
        description: 'Cloud-based platform for hosting Git repositories and enabling collaborative software development.'
      },
      {
        name: 'Prisma ORM',
        description: 'Next-generation Node.js and TypeScript ORM for type-safe database queries, schema migrations, and modeling.'
      },
      {
        name: 'Express.js',
        description: 'Fast, unopinionated, minimalist web framework for Node.js RESTful API architectures.'
      },
      {
        name: 'Supabase',
        description: 'Open-source Firebase alternative providing PostgreSQL database, instant APIs, authentication, and real-time subscriptions.'
      },
      {
        name: 'PostgreSQL',
        description: 'Powerful, open-source object-relational database system with advanced query optimization and Row Level Security.'
      },
      {
        name: 'Arduino',
        description: 'Open-source electronics platform for building digital devices with programmable microcontrollers.'
      },
      {
        name: 'ESP32',
        description: 'Low-cost, low-power microcontroller with integrated Wi-Fi and Bluetooth for IoT applications.'
      },
      {
        name: 'Chart.js',
        description: 'JavaScript library for creating interactive and animated data visualizations in the browser.'
      },
      {
        name: 'Google Colab',
        description: 'Cloud-based Jupyter notebook environment for running Python code with free GPU/TPU access.'
      },
      {
        name: 'VS Code',
        description: 'Lightweight yet powerful source code editor by Microsoft with rich extension ecosystem support.'
      }
    ]
  }
];

// ─── EXPERIENCE ───────────────────────────────────────────────────────────────
export const experiences = [
  {
    id: 'exp-aslab-embedded',
    role: 'Teaching Assistant - Embedded Systems Course',
    company: 'Universitas Syiah Kuala',
    location: 'Banda Aceh, Indonesia',
    period: 'Aug 2025 - Dec 2025',
    type: 'Part-time',
    logo: '/images/logos/usk.svg',
    description:
      'Assisted students in understanding embedded systems through microcontroller-based projects, lab sessions, and hands-on work with development boards, sensors, and actuators. Evaluated lab assignments and collaborated with the course instructor to prepare practical materials while strengthening technical and mentoring skills.',
    responsibilities: [],
    technologies: ['Microcontroller', 'Sensors', 'Actuators', 'C/C++'],
    images: []
  },
  {
    id: 'exp-aslab-rpl',
    role: 'Teaching Assistant - Software Engineering Course',
    company: 'Universitas Syiah Kuala',
    location: 'Banda Aceh, Indonesia',
    period: 'Jan 2025 - Jun 2025',
    type: 'Part-time',
    logo: '/images/logos/usk.svg',
    description:
      'Assisted students in understanding software engineering concepts through project guidance, practical exercises, Q&A sessions, and assignment evaluation. Collaborated with the course instructor to prepare learning materials while strengthening mentoring, communication, and technical skills.',
    responsibilities: [],
    technologies: ['Software Engineering', 'UML', 'Agile', 'Git'],
    images: []
  },
  {
    id: 'exp-pln-intern',
    role: 'Intern - Communication Division',
    company: 'PT PLN (Persero) UID Aceh',
    location: 'Banda Aceh, Indonesia',
    period: 'Dec 2024 — Jan 2025',
    type: 'Internship',
    logo: '/images/logos/pln.png',
    description:
      'Assisted in producing the PodcaStroom podcast, contributing to content and technical setup while reaching around 200 monthly views and 100 new subscribers. Developed the Desa Berdaya PLN UID Aceh website and documented PLN events by capturing and editing 500+ photos and videos for digital archives and publication.',
    responsibilities: [
      'Assisted in producing the PodcaStroom podcast with technical and content preparation.',
      'Developed the responsive Desa Berdaya PLN UID Aceh website.',
      'Documented PLN events by capturing and editing 500+ photos and videos for archives and media publication.'
    ],
    technologies: ['Web Development', 'Photography', 'Videography', 'Media Production'],
    images: [
      { src: '/About/Experience/PLN/1.JPG', caption: 'Producing PodcaStroom' },
      { src: '/About/Experience/PLN/2.jpg', caption: 'Producing Website Desa Berdaya' },
      { src: '/About/Experience/PLN/3.jpg', caption: 'Documenting PLN Events' }
    ]
  },
  {
    id: 'exp-LO-PON',
    role: 'Liaison Officer',
    company: 'Pekan Olahraga Nasional (PON) XXI Aceh-Sumatera Utara',
    location: 'Banda Aceh, Indonesia',
    period: 'Sep 2024 — Oct 2024',
    type: 'Seasonal',
    logo: '/images/logos/pon.png',
    description:
      'Acted as a primary point of contact, facilitating communication between athletes, officials, and the organizing committee while coordinating logistics, schedules, and participant needs. Contributed to the success of PON XXI by ensuring effective communication and providing high-quality support to all stakeholders.',
    responsibilities: [
      'Primary point of contact for athletes, team officials, and the organizing committee.',
      'Coordinated transport logistics, venue schedules, and accommodation assistance.',
      'Ensured top-tier communication flow and rapid response to event challenges.'
    ],
    technologies: ['Event Management', 'Public Relations', 'Logistics Planning'],
    images: [
      { src: '/About/Experience/PON/1.jpg', caption: 'Opening Ceremony' },
      { src: '/About/Experience/PON/2.jpg', caption: 'Certification' },
    ]
  },
  {
    id: 'exp-BINER',
    role: 'Chairperson - Bina Islami Aneuk Komputer (BINER 7.0)',
    company: 'Himpunan Mahasiswa Teknik Informatika Universitas Syiah Kuala',
    location: 'Banda Aceh, Indonesia',
    period: 'Oct 2023',
    type: 'Seasonal',
    logo: '/images/logos/HIMATEKKOM.png',
    description:
      'Successfully led BINER 7.0 under the theme “How to Reach Society 5.0 with Islamic Values,” overseeing planning, budgeting, team coordination, and event execution. Promoted the integration of Society 5.0 concepts with Islamic values throughout the event.',
    responsibilities: [
      'Led the entire organizing committee of BINER 7.0.',
      'Managed event scheduling, budgeting, resource allocation, and guest speaker coordination.',
      'Delivered a successful event bridging technology with community values.'
    ],
    technologies: ['Leadership', 'Event Management', 'Budgeting', 'Public Speaking'],
    images: [
      { src: '/About/Experience/BINER/1.jpg', caption: 'Welcoming Ceremony' },
      { src: '/About/Experience/BINER/2.jpg', caption: 'During event' },
      { src: '/About/Experience/BINER/3.jpg', caption: 'Commitee member' },
    ]
  },
  {
    id: 'exp-COSITE',
    role: 'Commitee Member - IC-COSITE 2023',
    company: 'Universitas Syiah Kuala / IEEE Indonesia Section',
    location: 'Banda Aceh, Indonesia',
    period: 'Aug 2023',
    type: 'Seasonal',
    logo: '/images/logos/ieee.svg',
    description:
      'The IEEE International Conference on Computer Science, Information Technology, and Electrical Engineering (IC-COSITE 2023) is an annual conference organized by the IEEE (Institute of Electrical and Electronics Engineers) Indonesia Section. In this conference, I was responsible for assisting in the smooth running of the conference, including event organization, speaker coordination, and participant assistance. In addition, I was also responsible for documenting the event by capturing and editing photos and videos.',
    responsibilities: [
      'Assisted international speakers and conference attendees.',
      'Handled audiovisual setups and conference documentation.',
      'Contributed to the publication and media archive of IEEE IC-COSITE 2023.'
    ],
    technologies: ['IEEE Conference', 'Event Coordination', 'Documentation'],
    images: [
      { src: '/About/Experience/COSITE/1.jpg', caption: 'Opening ceremony' },
      { src: '/About/Experience/COSITE/2.jpg', caption: 'During event' },
      { src: '/About/Experience/COSITE/3.jpg', caption: 'Commitee member' },
    ]
  },
];

// ─── EDUCATION ────────────────────────────────────────────────────────────────
export const education = [
  {
    id: 'edu-usk',
    degree: 'Bachelor of Computer Engineering',
    institution: 'Universitas Syiah Kuala',
    location: 'Banda Aceh, Indonesia',
    period: '2022 — 2026',
    logo: '/images/logos/usk.svg',
    description:
      'Graduated in Computer Engineering with strong interests in software development, artificial intelligence, UI/UX design, machine learning, IoT, and embedded systems.',
    details: [
      'Studied software engineering, artificial intelligence, machine learning, computer networks, IoT, and embedded systems.',
      'Developed various academic projects involving web development, microcontrollers, sensors, and intelligent systems.',
      'Gained experience in research, system development, data analysis, and user-centered interface design.',
      'Completed a final project focused on sentiment analysis using Machine Learning and Transformer-based models.'
    ],
    images: [
      { src: '/About/Education/USK/1.jpg', caption: 'Universitas Syiah Kuala Campus' },
      { src: '/About/Education/USK/2.jpeg', caption: 'Final Thesis Defense' },
      { src: '/About/Education/USK/3.jpeg', caption: 'During my final year' }
    ]
  },
  {
    id: 'edu-sma',
    degree: 'Science (Mathematics and Natural Sciences)',
    institution: 'SMA Negeri 3 Banda Aceh',
    location: 'Banda Aceh, Indonesia',
    period: '2019 — 2022',
    logo: '/images/logos/sman3.png',
    description:
      'Completed senior high school education while developing an interest in technology, science, and digital creativity.',
    details: [
      'Successfully curated and created captivating content for the KPS (Knowledge Posters in Smantig) project, resulting in 20% increase in user engagement and 600 new followers over a month.',
      'Successfully spearheaded the conceptualization and execution of visually appealing designs for school events, resulting in a 30% increase in event attendance.',
      'Successfully developed and implemented innovative content formats, such as video tutorials and infographics.',
      'Successfully produced captivating graphics and layouts for school publications, resulting in 10% increase in readership.'
    ],
    images: [
      { src: '/About/Education/SMA/2.jpg', caption: 'SMA Negeri 3 Banda Aceh' },
      { src: '/About/Education/SMA/3.jpeg', caption: 'Graduation ceremony' }
    ]
  },
];

// ─── ORGANIZATIONS ────────────────────────────────────────────────────────────
export const organizations = [
  {
    id: 'org-himatekkom-ketum-psdm',
    role: 'Head of the Human Resources Development Division',
    organization: 'Himpunan Mahasiswa Teknik Komputer USK (HIMATEKKOM)',
    location: 'Universitas Syiah Kuala',
    period: 'Mar 2025 — Dec 2025',
    logo: '/images/logos/HIMATEKKOM.png',
    description:
      'Led human resource development initiatives by strengthening relationships among members, alumni, and external partners, while managing internal programs that increased member participation by 40%. Also coordinated large-scale activities such as Computer Outbonding to enhance teamwork, leadership, and organizational engagement.',
    responsibilities: [],
    images: []
  },
  {
    id: 'org-bem-humas',
    role: 'Member of the Student Relations Division',
    organization: 'Badan Eksekutif Mahasiswa Fakultas Teknik (BEM-FT)',
    location: 'Universitas Syiah Kuala',
    period: 'Mar 2024 — Dec 2024',
    logo: '/images/logos/BEM-FT.png',
    description:
      'Developed strong communication, leadership, teamwork, problem-solving, negotiation, and event management skills while building relationships with 10 student organizations within the faculty and coordinating collaborative events.',
    responsibilities: [],
    images: []
  },
  {
    id: 'org-himatekkom-waketum-humas',
    role: 'Vice Chair of the Student Relations',
    organization: 'Himpunan Mahasiswa Teknik Komputer USK (HIMATEKKOM)',
    location: 'Universitas Syiah Kuala',
    period: 'Mar 2024 — Dec 2024',
    logo: '/images/logos/HIMATEKKOM.png',
    description:
      'Built strong relationships with other student associations, alumni, and technology companies while coordinating large-scale programs such as Computer Outbonding. Successfully managed events that increased student participation in association activities by 40%.',
    responsibilities: [],
    images: []
  },
  {
    id: 'org-himatekkom-kesma',
    role: 'Member of Student Welfare Division.',
    organization: 'Himpunan Mahasiswa Teknik Komputer USK (HIMATEKKOM)',
    location: 'Universitas Syiah Kuala',
    period: 'Mar 2023 — Feb 2024',
    logo: '/images/logos/HIMATEKKOM.png',
    description:
      'Supported student welfare by promoting environmental responsibility through a campus cleanliness duty system and assisting students facing financial difficulties by providing information on scholarships, emergency aid, and installment payment options.',
    responsibilities: [],
    images: []
  },
  {
    id: 'org-OSIS',
    role: 'Head of Technology Information and Communication Division',
    organization: 'MPK-OSIS SMA Negeri 3 Banda Aceh',
    location: 'SMA Negeri 3 Banda Aceh',
    period: 'Sep 2020 — Sep 2021',
    logo: '/images/logos/sman3.png',
    description:
      'Successfully created engaging visual content for KPS and various school activities, including social media posts, event designs, video tutorials, infographics, and school publications.',
    responsibilities: [],
    images: [
      { src: '/About/Organization/OSIS/1.jpeg', caption: 'Inauguration Ceremony' },
      { src: '/About/Organization/OSIS/2.jpeg', caption: 'Knowledge Posters in Smantig' },
      { src: '/About/Organization/OSIS/3.jpeg', caption: 'MPK-OSIS Smantig’s Instagram' },
    ]
  }
];

// ─── CERTIFICATIONS ───────────────────────────────────────────────────────────
export const certifications = [
  {
    id: 'cert-1',
    name: 'UI/UX Design Masterclass',
    issuer: 'Interaction Design Foundation',
    date: '2024',
    credentialId: 'IDF-882910',
    credentialUrl: '',
    description: 'Advanced user research, wireframing, interactive prototyping, and design systems.',
    images: [
      { src: '/About/Experience/PON/2.jpg', caption: 'Certificate preview' }
    ]
  },
  {
    id: 'cert-2',
    name: 'Machine Learning & NLP Specialization',
    issuer: 'DeepLearning.AI & Coursera',
    date: '2024',
    credentialId: 'DLAI-39182',
    credentialUrl: '',
    description: 'Transformer models, IndoBERT, IndoBERTweet fine-tuning, and sentiment analysis pipelines.',
    images: []
  },
  {
    id: 'cert-3',
    name: 'Frontend Web Development (React & Vite)',
    issuer: 'Dicoding Indonesia',
    date: '2023',
    credentialId: 'DICODING-FE-772',
    credentialUrl: '',
    description: 'Component architecture, responsive layouts, Tailwind CSS, and state management.',
    images: []
  }
];

// ─── PROJECT CATEGORIES ───────────────────────────────────────────────────────
export const projectCategories = [
  'All',
  'UI/UX Design',
  'Web',
  'Machine Learning',
  'IoT',
  'Other'
];

// ─── PROJECTS ─────────────────────────────────────────────────────────────────
export const projects = [
  {
    id: 1,
    slug: 'roblox-sentiment-analysis',
    title: 'Sentiment Analysis of Roblox Reviews',
    category: 'Machine Learning',
    year: '2026',

    description:
      'Sentiment analysis research on 40,298 Indonesian Roblox reviews from the Google Play Store, benchmarking SVM (TF-IDF), IndoBERT, and IndoBERTweet. IndoBERT achieved top performance with 88.91% accuracy and an 81.78% macro F1-score.',

    overview:
      'This research focuses on developing an Indonesian Roblox review dataset and comparing the sentiment classification efficacy of Support Vector Machine (SVM), IndoBERT, and IndoBERTweet across negative, neutral, and positive classes. A total of 50,000 raw reviews were extracted from the Google Play Store using web scraping. Following thorough data cleaning and deduplication, a curated dataset of 40,298 reviews (19,488 Negative, 2,471 Neutral, and 18,339 Positive) was established with an 80:10:10 train-validation-test split.',

    problem:
      'Indonesian gaming community reviews feature highly informal slang, colloquial expressions, and linguistic nuances. Additionally, severe class imbalance in neutral reviews (representing only 6.13% of the dataset) introduced high semantic ambiguity, creating major classification bottlenecks for standard NLP architectures.',

    solution:
      'Engineered an end-to-end NLP research pipeline: automated Google Play Store web scraping, text preprocessing & automated sentiment pseudo-labeling with IndoBERT, 80:10:10 data partitioning, TF-IDF feature extraction for SVM, and fine-tuning contextual Transformer models (IndoBERT and IndoBERTweet) with hyperparameter optimization (learning rate 10⁻⁶, batch size 64, 10 epochs).',

    features: [
      'Final Dataset of 40,298 Reviews: 19,488 Negative (48.36%), 2,471 Neutral (6.13%), and 18,339 Positive (45.51%) curated from 50,000 scraped reviews',
      'Top-Performing IndoBERT Model: Achieved 88.91% Accuracy, 79.09% Macro Precision, 86.79% Macro Recall, and an 81.78% Macro F1-Score',
      'IndoBERTweet Evaluation: Reached 86.63% Accuracy, 76.02% Macro Precision, 83.44% Macro Recall, and a 78.42% Macro F1-Score',
      'Baseline SVM Benchmark (RBF Kernel, C=1, gamma=scale, TF-IDF): Attained 84.07% Accuracy, 74.70% Macro Precision, 72.52% Macro Recall, and a 73.49% Macro F1-Score',
      'Contextual Transformer Superiority: Empirically demonstrated that pre-trained Transformer architectures significantly outperform classical ML models in capturing contextual semantics from informal Indonesian text',
      'Imbalance & Ambiguity Analysis: In-depth evaluation of classification challenges on neutral sentiment classes due to data sparsity and ambiguous user expressions'
    ],

    role: 'Machine Learning Researcher',

    image: '/projects/roblox/roblox-sentiment-display.jpg',
    images: [
      {
        src: '/projects/roblox/roblox-sentiment-display.jpg',
        caption: 'Sentiment Analysis Dashboard & IndoBERT NLP Architecture'
      },
      {
        src: '/projects/roblox/model-performance-comparison.png',
        caption: 'Model Performance Comparison Table (SVM vs. IndoBERT vs. IndoBERTweet)'
      },
      {
        src: '/projects/roblox/sentiment-distribution.png',
        caption: 'Sentiment Class Distribution (Negative: 19,488, Neutral: 2,471, Positive: 18,339)'
      },
      {
        src: '/projects/roblox/research-flowchart.png',
        caption: 'NLP Research Methodology & Model Convergence Flowchart'
      }
    ],

    technologies: [
      'Python',
      'IndoBERT',
      'IndoBERTweet',
      'SVM',
      'Transformers',
      'TF-IDF',
      'NLP',
      'Scikit-learn',
      'Web Scraping',
      'Google Colab'
    ],

    links: {
      github: 'https://github.com/dappahsn/Sentiment-Analysis-of-Roblox-Reviews',
      demo: ''
    },

    featured: true
  },

  {
    id: 2,
    slug: 'cash-in-point-of-sale',
    title: 'Cash.in - Point of Sale (POS) & Business Management',
    category: 'Web',
    year: '2026',

    description:
      'A modern Point of Sale (POS) and business management web application built with Next.js 16 (App Router), React 19, TypeScript, and Tailwind CSS v4. Features rapid barcode/SKU checkout, 1:1 image auto-cropping, QRIS payments, thermal receipt printing, real-time stock control, multi-role RBAC, and Excel reports.',

    overview:
      'Cash.in is a high-performance digital Point of Sale (POS) and store management platform designed for micro, small, and medium enterprises (UMKM), F&B outlets, and retail stores. Built with Next.js 16 App Router and client-side reactive architectures, Cash.in delivers sub-second checkout speeds on desktop, tablet, and mobile browsers without requiring expensive server database overhead. It provides multi-role authentication (Owner & Cashier), dynamic SKU auto-generation, QRIS integration with store NMID, thermal receipt printing (58mm/80mm), operational expense tracking, and comprehensive profit & loss analytics.',

    problem:
      'Traditional cash registers and enterprise POS systems are often slow, costly with high recurring subscription fees, difficult to configure on standard tablets/smartphones, and lack intuitive inventory alerts, staff role restrictions, and instant digital QRIS payment integration for growing UMKM businesses.',

    solution:
      'Engineered a lightweight, zero-latency POS web application leveraging Next.js 16, TypeScript, Zustand state management, and an optimized LocalStorage data access engine. Built an ergonomic POS terminal with instant category filtering, automatic VAT (11%) & discount calculation, browser canvas 1:1 image compression (~15–25 KB), visual stock status indicators (Safe, Low, Out of Stock), Role-Based Access Control (RBAC), and Recharts financial reporting with SheetJS Excel export.',

    features: [
      'Ergonomic Point of Sale (POS) Terminal: Instant product search by name or barcode/SKU, category filtering, 1:1 product catalog, and smart cart drawer with auto VAT (11%) & bill discounts',
      'Flexible Payment & QRIS Integration: Supports cash payments with auto Rupiah change calculation and native store QRIS scanning with verified merchant name and NMID',
      'Thermal Receipt Printing: Integrated receipt generation for 58mm and 80mm thermal printers with custom business logos, active cashier attribution, and personalized footer notes',
      'Automated 1:1 Image Cropping & SKU Engine: In-browser canvas auto-cropping and compression (~15-25 KB) paired with smart category-based SKU generators (MIN-xxxx, MAK-xxxx, SNA-xxxx)',
      'Real-Time Inventory & Stock Warning: Dynamic stock tracking with color-coded status badges (Stok Aman, Stok Menipis, Habis) and complete stock mutation adjustment logs',
      'Multi-Role Access Control (RBAC): Dedicated permissions for Store Owner (full financial analytics, staff management, Excel export) and Cashiers (POS checkout & stock lookup with route guards)',
      'Financial Analytics & Profit/Loss Reports: Interactive Recharts visualizations tracking 7-day revenue trends, payment method proportions, staff sales performance, and P&L metrics',
      'Data Portability & Bilingual Support: One-click Excel (XLSX) and CSV reporting via SheetJS with full bilingual localization in Bahasa Indonesia (default) and English'
    ],

    role: 'Full-Stack Web Developer & UI/UX Designer',

    image: '/projects/cashin/cashin-mockup.jpg',
    images: [
      {
        src: '/projects/cashin/cashin-mockup.jpg',
        caption: 'Cash.in Desktop Dashboard & Tablet POS Terminal Showcase'
      },
      {
        src: '/projects/cashin/cashin-pos.png',
        caption: 'Point of Sale (POS) Interface - 1:1 Product Grid, Category Filters & Real-Time Cart Checkout'
      },
      {
        src: '/projects/cashin/cashin-dashboard.png',
        caption: 'Main Store Dashboard - 7-Day Revenue Trend, Top Selling Products & Low Stock Warnings'
      },
      {
        src: '/projects/cashin/cashin-analytics.png',
        caption: 'Reports & Analytics - Gross/Net Revenue, Payment Method Donut Chart & Cashier Staff Performance'
      },
      {
        src: '/projects/cashin/cashin-logo.png',
        caption: 'Cash.in Official Logo & Visual Brand Identity'
      }
    ],

    technologies: [
      'Next.js 16',
      'React 19',
      'TypeScript',
      'Tailwind CSS',
      'Zustand',
      'Recharts',
      'SheetJS (XLSX)',
      'Lucide React',
      'RBAC',
      'Canvas API',
      'i18n'
    ],

    links: {
      github: 'https://github.com/dappahsn/Cash.in',
      demo: 'https://daffahusen-cash-in.vercel.app/'
    },

    featured: true
  },

  {
    id: 3,
    slug: 'catat-in-personal-finance',
    title: 'catat.in - Personal Finance & Cashflow Tracker',
    category: 'Web',
    year: '2026',

    description:
      'A modern, mobile-first personal finance tracker and Progressive Web App (PWA) built with React 19, TypeScript, Tailwind CSS v4, and Supabase. Features multi-account balance management, automated Rupiah formatting, Recharts cashflow analytics, Google OAuth, and encrypted cloud sync.',

    overview:
      'catat.in is a responsive, high-performance personal finance tracking web application and installable Progressive Web App (PWA). Designed with a mobile-first philosophy, elegant Charcoal Dark (#0d0f12) and Emerald Green visual aesthetics, and zero layout shift, it empowers users to record daily income, expenses, and inter-account transfers, manage multi-source balances (Bank, E-Wallet, Cash), and visualize financial health through interactive Recharts analytics.',

    problem:
      'Individuals managing funds across multiple bank accounts, e-wallets, and cash often struggle with fragmented records, inaccurate balance oversight, and tedious manual accounting. Many existing finance tools are either overly complex with unnecessary enterprise features or lack smooth mobile responsiveness and real-time multi-account synchronization.',

    solution:
      'Engineered a lightweight, privacy-focused PWA powered by Supabase PostgreSQL with strict Row Level Security (RLS) and Google OAuth authentication. Developed a frictionless transaction recording workflow with dynamic Rupiah currency formatting, mutation-based balance calculations, Recharts donut and cashflow visualizations, custom category management with emoji pickers, dual-theme support, multi-language (i18n), and JSON/CSV backup and export capabilities.',

    features: [
      'Smart Transaction Logging: Frictionless income (+), expense (-), and internal transfer (↔) tracking with real-time balance validation and auto-formatted IDR currency inputs',
      'Multi-Account Balance Engine: Centralized management for Bank (BCA, Mandiri, BRI, SeaBank), Cash, and E-Wallets (GoPay, OVO, Dana, ShopeePay) with mutation-derived dynamic balances',
      'Interactive Financial Analytics: Visualized cashflow breakdown with responsive Recharts donut charts, net income/expense summary cards, and category percentage distributions',
      'Dynamic Category Customization: Custom income and expense category creation with an intuitive emoji picker for personalized expense tagging',
      'Progressive Web App (PWA) & Mobile-First UX: Fully installable native-like PWA experience on Android, iOS, and Desktop with offline service workers and zero layout shift (scrollbar-gutter: stable)',
      'Enterprise-Grade Security & Cloud Sync: Google OAuth 2.0 authentication powered by Supabase with Row Level Security (RLS) ensuring strict per-user database isolation',
      'Data Portability & Export: Full JSON backup/restore with integrity verification and Excel-ready CSV export with UTF-8 BOM encoding',
      'Internationalization & Daily Reminders: Dual-language support (Bahasa Indonesia & English) and customizable daily browser notification reminders'
    ],

    role: 'Full-Stack Developer & UI/UX Designer',

    image: '/projects/catatin/catatin-mockup.jpg',
    images: [
      {
        src: '/projects/catatin/catatin-mockup.jpg',
        caption: 'catat.in Mobile App Dual Mockup (Transactions Feed & Financial Recap UI)'
      },
      {
        src: '/projects/catatin/catatin-transactions-real.png',
        caption: 'Live Transactions Screen - Real-Time Balance (Rp 1.600.000), Date Period Filters & Cashflow History'
      },
      {
        src: '/projects/catatin/catatin-recap-real.png',
        caption: 'Financial Recap & Expense Distribution - Interactive Donut Chart, Net Difference & Category Breakdown'
      },
      {
        src: '/projects/catatin/catatin-logo-text.png',
        caption: 'catat.in Official Brand Identity & Logotype'
      }
    ],

    technologies: [
      'React 19',
      'TypeScript',
      'Vite',
      'Tailwind CSS',
      'Supabase',
      'PostgreSQL',
      'PWA',
      'Recharts',
      'Lucide React',
      'Google OAuth',
      'i18n'
    ],

    links: {
      github: 'https://github.com/dappahsn/catat.in',
      demo: 'https://daffahusen-finance.vercel.app/'
    },

    featured: true
  },

  {
    id: 4,
    slug: 'court-in-sports-booking',
    title: 'court.in - Sports Venue Booking & Management Platform',
    category: 'Web',
    year: '2026',

    description:
      'A full-stack sports court reservation and venue management platform built with React 19, Vite, Tailwind CSS v4, Zustand, Node.js Express 5, PostgreSQL, and Prisma ORM. Features conflict-free slot booking, 15-minute QRIS payment countdown, digital E-Tickets with QR codes, verified reviews, and a multi-role admin venue management dashboard.',

    overview:
      'court.in is a comprehensive full-stack digital sports reservation and facility management platform designed to eliminate schedule clashes and manual booking friction for Futsal, Badminton, and Padel venues. Architected as a modular monorepo, the client features a high-performance React 19 and Tailwind CSS v4 frontend with Zustand state management, while the backend is powered by Node.js, Express 5, Prisma ORM, and PostgreSQL. The platform integrates dynamic QRIS payment workflows via Midtrans, automated 15-minute slot holding timers, verifiable digital E-Tickets with barcode/QR rendering, and a robust administrative portal for real-time venue scheduling, revenue analytics, and staff access control.',

    problem:
      'Sports facility reservations in Indonesia commonly rely on manual WhatsApp messaging and paper logs, resulting in frequent double-booking conflicts, lack of real-time slot visibility, cumbersome cash reconciliation, and fake or unverified customer reviews.',

    solution:
      'Engineered an atomic double-booking prevention engine powered by PostgreSQL transactions and Prisma ORM, backed by a 15-minute QRIS checkout reservation timer that automatically releases unpaid slots. Built an interactive hourly time-slot matrix (07:00–23:00) with visual availability states, instant cash/QRIS checkout options, cryptographic E-Ticket generation, and review integrity gates that only permit verified players with completed bookings to submit ratings and feedback.',

    features: [
      'Real-Time Schedule Matrix: Interactive hourly booking grid (07:00–23:00) across Futsal, Badminton, and Padel courts with instant visual slot statuses (Available, Selected, Booked)',
      'Atomic Double-Booking Prevention: Database-level transaction locks and backend concurrency middleware ensuring zero overlapping reservations',
      '15-Minute QRIS Payment Hold: Automated reservation locking with a real-time countdown timer that auto-cancels expired orders and restores public slot availability',
      'Digital E-Ticket with QR Code: Official ticket issuance featuring unique booking references (TKT-YYYY-MMDD-XXX), fee breakdown, and PDF download/print capability',
      'Verified Review Integrity System: Anti-spam rating mechanism strictly restricted to users with verified COMPLETED booking sessions, complete with venue admin replies',
      'Comprehensive Admin Management Portal: Executive analytics dashboard tracking venue revenue, occupancy rates, live slot scheduler, staff access permissions, and business operating hours',
      'Multi-Role Access Control (RBAC): Dedicated roles for Customers, Venue Admins, and Operational/Cashier Staff with JWT-secured route guards',
      'Modern Sporty UI & Motion Design: Built with Tailwind CSS v4 tokenized themes, smooth scroll-driven animations, responsive bento grids, and dynamic metric counters'
    ],

    role: 'Full-Stack Developer & UI/UX Designer',

    image: '/projects/courtin/courtin-mockup.jpg',
    images: [
      {
        src: '/projects/courtin/courtin-mockup.jpg',
        caption: 'court.in Multi-Device Showcase - Desktop Venue Management & Tablet Booking Interface'
      },
      {
        src: '/projects/courtin/courtin-explore.png',
        caption: 'Explore Courts Catalog - Sport Filters (Futsal, Badminton, Padel), Price Ranges & Real-Time Availability'
      },
      {
        src: '/projects/courtin/courtin-detail.png',
        caption: 'Court Detail & Slot Booking - High-Definition Facility Specs, Pricing & Interactive Hourly Schedule'
      },
      {
        src: '/projects/courtin/courtin-home.png',
        caption: 'court.in Landing Page - Hero Presentation, Quick Search Bar & Sport Discovery'
      },
      {
        src: '/projects/courtin/courtin-about.png',
        caption: 'court.in About Ecosystem - Company Mission, Dynamic Customer Metrics & World-Class Service'
      },
      {
        src: '/projects/courtin/courtin-contact.png',
        caption: 'court.in Support & FAQ - Multi-Channel Help Center (WhatsApp, Email) & Instant Inquiries'
      },
      {
        src: '/projects/courtin/courtin-logo-horizontal.png',
        caption: 'court.in Official Brand Identity & Logotype'
      }
    ],

    technologies: [
      'React 19',
      'Vite',
      'Tailwind CSS v4',
      'Zustand',
      'Node.js',
      'Express.js 5',
      'PostgreSQL',
      'Prisma ORM',
      'Midtrans QRIS',
      'JWT Auth',
      'Lucide React',
      'RESTful API'
    ],

    links: {
      github: 'https://github.com/dappahsn/Court.in',
      demo: 'https://court-in.vercel.app/'
    },

    featured: true
  },

  {
    id: 5,
    slug: 'biocompost-buddy',
    title: 'BioCompost Buddy - Smart IoT Composting System',
    category: 'IoT',
    year: '2025',

    description:
      'A nationally funded Top 180 Innovillage project delivering an integrated smart IoT composting machine and web monitoring platform with automated shredding, mechanized aeration, and multi-sensor fermentation tracking for rural food security in Aceh Besar.',

    overview:
      'BioCompost Buddy was selected as a Top 180 Nationally Funded Social Project in the Innovillage 2025/2026 competition (Telkom University / BUMN). Implemented directly in Gampong Cadek, Baitussalam, Aceh Besar, the system combines dual-chamber mechanical hardware (organic waste shredder and automated mixing paddles) with an ESP32 IoT telemetry node (DHT22, soil moisture, and MQ-6 gas sensors). The solution empowers 50 household farmers and village enterprise (BUMDes) caretakers to monitor real-time fermentation metrics through an interactive web portal, producing consistent, odor-free organic fertilizer while advancing community-based circular economy.',

    problem:
      'Household farmers in Gampong Cadek previously processed agricultural and organic waste using slow, manual methods resulting in inconsistent fertilizer quality, unmonitored anaerobic gas spikes, and unpredictable decomposition periods, with zero sensor or digital monitoring capabilities in the village.',

    solution:
      'Engineered an integrated dual-chamber composting prototype equipped with a high-torque mechanical waste shredder, motorized mixing paddles, and an ESP32 IoT telemetry node wired to DHT22, capacitive soil moisture, and MQ-6 gas sensors. Deployed an interactive web portal featuring real-time readiness gauges (25–30°C, 40–60% moisture, <1000 ppm gas) and wireless manual override controls.',

    features: [
      'Top 180 Innovillage National Finalist & Funded Project: Recognized and funded under the Innovillage 2025/2026 national social innovation competition by Telkom University & BUMN',
      'Dual-Chamber Automated Mechanical System: High-torque organic waste shredder blade to minimize particle sizes paired with automated aeration mixing paddles',
      'Multi-Sensor IoT Telemetry Node: ESP32 microcontroller reading DHT22 (ambient temp/humidity), soil moisture probes, and MQ-6 (methane/ammonia) gas sensors',
      'Real-Time Fermentation Analytics: Automated algorithm detecting optimal compost maturity thresholds (25–30°C, 40–60% moisture, <1000 ppm gas)',
      'Interactive Web Dashboard & 3D Visualizer: Responsive web platform with live sensor telemetry gauges, wireless motor activation, and 3D architectural models',
      'Social Impact & Field Implementation: Directly deployed with village socialization, handover, and BUMDes training for 50 local farmers in Gampong Cadek, Aceh Besar'
    ],

    role: 'Lead IoT Engineer & Full-Stack Developer',

    image: '/projects/biocompost-buddy/biocompost-banner.jpg',
    images: [
      {
        src: '/projects/biocompost-buddy/biocompost-banner.jpg',
        caption: 'Official Innovillage 2025/2026 Project Banner (Sosialisasi Teknologi Pengolahan Kompos di Gampong Cadek)'
      },
      {
        src: '/projects/biocompost-buddy/biocompost-handover.jpg',
        caption: 'Innovillage Top 180 Project - Handover & Village Socialization with Local Farmers'
      },
      {
        src: '/projects/biocompost-buddy/biocompost-assembly.jpg',
        caption: 'Hardware Assembly, IoT Sensor Calibration & Field Testing Session'
      },
      {
        src: '/projects/biocompost-buddy/biocompost-mockup.png',
        caption: 'BioCompost Buddy Web Platform Laptop Showcase (Hero & 3D Design Stage)'
      },
      {
        src: '/projects/biocompost-buddy/biocompost-3d-model.png',
        caption: 'Interactive 3D Dual-Chamber Structure (Organic Waste Shredder & Sensor Mixing Tank)'
      },
      {
        src: '/projects/biocompost-buddy/biocompost-features.png',
        caption: 'Core System Capabilities (Real-Time Monitoring, Automatic Mixing, Shredder, IoT Dashboard)'
      }
    ],

    technologies: [
      'ESP32',
      'IoT',
      'DHT22 Sensor',
      'MQ-6 Gas Sensor',
      'Soil Moisture Sensor',
      'Embedded C/C++',
      'JavaScript',
      'HTML5/CSS3',
      'Bootstrap 5',
      'Smart Agriculture'
    ],

    links: {
      github: 'https://github.com/dappahsn/BioCompostBuddy',
      demo: 'https://dappahsn.github.io/BioCompostBuddy/'
    },

    featured: true
  },

  {
    id: 6,
    slug: 'desa-berdaya-pln',
    title: 'Desa Berdaya PLN',
    category: 'Web',
    year: '2025',

    description:
      'A comprehensive community empowerment web portal built for PT PLN (Persero) UID Aceh, showcasing local Acehnese UMKM products, village initiatives, educational English courses, and sustainability waste management programs.',

    overview:
      'Desa Berdaya PLN is a multi-page community empowerment portal developed during an internship at PT PLN (Persero) Unit Induk Distribusi Aceh. The platform centralizes and visualizes CSR initiatives across Aceh villages, featuring interactive catalogs for local UMKM artisans, village distribution maps across Aceh, educational programs like GM English Course, and environmental sustainability projects like Bank Sampah USK.',

    problem:
      'Prior to this platform, information regarding PLN-supported village programs, empowered UMKM micro-enterprises, and community development initiatives across Aceh was fragmented and difficult for the public and stakeholders to discover.',

    solution:
      'Engineered a modern, responsive web application featuring a multi-page navigation architecture, dynamic category filtering for local crafts and traditional culinary products (Kupiah Meukeutop, Songket, Kue Bhoi, Ikan Keumamah), interactive village distribution mapping, image carousels for program documentation, interactive FAQ accordions, and an integrated contact system.',

    features: [
      'Comprehensive Landing Portal: Dynamic hero carousel, program highlight cards, interactive Aceh distribution map, FAQ accordion, and inquiry form',
      'UMKM Product Showcase & Filter: Categorized gallery filtering across Food, Clothing, Headwear, Bags, Handicrafts, and Household Tools',
      'Education & English Course Hub: Dedicated module showcasing the GM English Course initiative in Gampong Geuceu Meunara to improve youth global competence',
      'Environmental Sustainability / Bank Sampah: Documentation of organic & inorganic waste processing machines (hydraulic presses, shredders, grinders) at USK',
      'Responsive Multi-Device Layout: Built with mobile-first principles, fluid Bootstrap grid system, and high-contrast accessible typography',
      'Interactive Media Carousels: Integrated multi-image sliders for event documentation and program reporting'
    ],

    role: 'Front-End Web Developer',

    image: '/projects/desa-berdaya/desa-berdaya-cover.jpg',
    images: [
      {
        src: '/projects/desa-berdaya/desa-berdaya-cover.jpg',
        caption: 'Desa Berdaya PLN Hero Banner & Official Welcome Interface'
      },
      {
        src: '/projects/desa-berdaya/desa-berdaya-home.png',
        caption: 'Desa Berdaya PLN Home Page & Interactive Village Distribution'
      },
      {
        src: '/projects/desa-berdaya/desa-berdaya-umkm.png',
        caption: 'Local Acehnese UMKM Product Catalog & Dynamic Filter System'
      },
      {
        src: '/projects/desa-berdaya/desa-berdaya-kursus.png',
        caption: 'GM English Course - Youth Education Empowerment Page'
      },
      {
        src: '/projects/desa-berdaya/desa-berdaya-bank-sampah.png',
        caption: 'Bank Sampah & Environmental Waste Management System'
      }
    ],

    technologies: [
      'HTML5',
      'CSS3',
      'JavaScript',
      'Bootstrap 5',
      'Chart.js',
      'Google Maps API',
      'Responsive Web Design',
      'GitHub Pages'
    ],

    links: {
      github: 'https://github.com/dappahsn/Desa-Berdaya-PLN-UID-ACEH',
      demo: 'https://dappahsn.github.io/Desa-Berdaya-PLN-ACEH/index.html'
    },

    featured: true
  },

  {
    id: 7,
    slug: 'adaptive-sobel-edge-detection',
    title: 'Adaptive Sobel Edge Detection',
    category: 'Machine Learning',
    year: '2024',

    description:
      'An interactive Computer Vision web application built with Python, OpenCV, and Streamlit, implementing adaptive thresholding on the classical Sobel operator for robust real-time image edge detection.',

    overview:
      'Developed under the guidance of Kahlil Muchtar, Ph.D. at Universitas Syiah Kuala, this Computer Vision project enhances traditional Sobel edge detection by dynamically adjusting gradient sensitivity based on local neighborhood contrast and pixel intensities. Built with an intuitive Streamlit web interface, users can upload custom imagery, dynamically tune threshold parameters, and inspect real-time edge segmentation maps.',

    problem:
      'Standard Sobel operators rely on fixed global thresholding and rigid convolution kernels, causing poor boundary detection on images with uneven illumination, low contrast, or noisy backgrounds.',

    solution:
      'Engineered an adaptive Sobel operator algorithm in Python with OpenCV and NumPy that computes directional gradient magnitudes (Gx and Gy) with local neighborhood threshold adaptation. Deployed on Streamlit Cloud with an interactive web UI allowing instant parameter manipulation and side-by-side visual analysis.',

    features: [
      'Adaptive Gradient Sensitivity: Dynamically adjusts threshold levels based on local image contrast to minimize noise while preserving critical boundary details',
      'Interactive Streamlit Web Dashboard: Upload custom images and tweak filter parameters (kernel size, sensitivity, threshold) with instant visual feedback',
      'Directional Gradient Computation: Calculates horizontal (Gx) and vertical (Gy) spatial gradient derivatives for complete 2D edge magnitude mapping',
      'Real-Time Computer Vision Pipeline: High-speed matrix convolutions powered by OpenCV and NumPy for seamless image rendering',
      'Academic Research Supervision: Guided by Kahlil Muchtar, Ph.D., bridging theoretical digital image processing with interactive web deployment'
    ],

    role: 'Computer Vision Developer',

    image: '/projects/computer-vision/sobel-display.jpg',
    images: [
      {
        src: '/projects/computer-vision/sobel-streamlit-overview.jpg',
        caption: 'Adaptive Sobel Edge Detection Streamlit Web Dashboard & Comparison Matrix'
      },
      {
        src: '/projects/computer-vision/sobel-streamlit-analysis.png',
        caption: 'Interactive Parameter Tuning (Manual Threshold vs. Otsu Adaptive Segmentation)'
      },
      {
        src: '/projects/computer-vision/sobel.png',
        caption: 'Directional Sobel Convolution Kernels & Gradient Operator Matrix'
      },
      {
        src: '/projects/computer-vision/sobel-display.jpg',
        caption: 'Computer Vision Real-Time Analysis & Dynamic Gradient Pipeline'
      }
    ],

    technologies: [
      'Python',
      'OpenCV',
      'Streamlit',
      'NumPy',
      'Computer Vision',
      'Image Processing',
      'Sobel Filter',
      'Streamlit Cloud'
    ],

    links: {
      github: 'https://github.com/dappahsn/Computer-Vision-Kelompok-1',
      demo: 'https://computer-vision-kelompok-1.streamlit.app/'
    },

    featured: false
  },

  {
    id: 8,
    slug: 'iepoma',
    title: 'IePoma - Smart Wastewater Recycling for Irrigation',
    category: 'IoT',
    year: '2024',

    description:
      'An IoT-enabled smart recycling and automated plant irrigation system that purifies rice washing wastewater for sustainable household and commercial agriculture.',

    overview:
      'Developed under the guidance of Rahmad Dawood at Universitas Syiah Kuala, IePoma is an automated smart irrigation and water conservation IoT prototype designed to recycle nutrient-rich rice washing wastewater. By integrating multi-stage mechanical filtration with Arduino microcontroller automation and real-time soil moisture sensors, the system automatically irrigates crops only when moisture levels drop below threshold, supporting SDG 6 (Clean Water and Sanitation) and SDG 12 (Responsible Consumption and Production).',

    problem:
      'Large amounts of rice washing wastewater from households and commercial restaurants ("Rumah Makan") are routinely discarded down drains, wasting valuable water and nutrient potential, while conventional irrigation systems lack sensor-driven automation and lead to excessive freshwater consumption.',

    solution:
      'Engineered an automated embedded IoT system featuring a multi-stage sediment filtration reservoir, soil moisture sensor probes, and automated solenoid/servo valve actuators controlled by an Arduino microcontroller. The system purifies greywater and delivers precise, automated drip irrigation based on real-time soil hydration data.',

    features: [
      'Sustainable Wastewater Recycling: Captures and filters rice washing greywater from culinary establishments to conserve potable freshwater',
      'Real-Time Soil Moisture Sensing: Continously monitors soil hydration levels to trigger automatic, data-driven irrigation cycles',
      'Multi-Stage Filtration Architecture: Integrated pre-filter and sediment filtration chamber to remove suspended solids prior to distribution',
      'Arduino Microcontroller Control: Robust embedded firmware managing sensor telemetry, threshold evaluation, and automated valve actuation',
      'SDG Alignment (SDG 6 & 12): Promotes responsible resource consumption, circular water economy, and urban micro-farming sustainability',
      'Academic Research Supervision: Guided by mentor Rahmad Dawood at Universitas Syiah Kuala'
    ],

    role: 'Embedded Systems & IoT Developer',

    image: '/projects/iepoma/iepoma-hardware-setup.jpg',
    images: [
      {
        src: '/projects/iepoma/iepoma-hardware-setup.jpg',
        caption: 'IePoma Physical Hardware Prototype - Rice Wastewater Recycling & Automated Irrigation Setup'
      },
      {
        src: '/projects/iepoma/iepoma-system-3d.png',
        caption: '3D CAD Prototype Model of Filtration Tank & Microcontroller Actuator'
      },
      {
        src: '/projects/iepoma/iepoma-top-view.png',
        caption: 'Top-Down Layout of Wastewater Reservoir, Pipeline & Garden Bed'
      },
      {
        src: '/projects/iepoma/iepoma-environment.png',
        caption: 'Implementation Environment Concept at Commercial Restaurant (Rumah Makan)'
      }
    ],

    technologies: [
      'Arduino',
      'Soil Moisture Sensor',
      'Filtration System',
      'Embedded C/C++',
      'IoT',
      'Automated Actuators',
      'Water Recycling',
      'Smart Agriculture'
    ],

    links: {
      github: 'https://github.com/dappahsn/Ie-Poma',
      demo: ''
    },

    featured: false
  },

  {
    id: 9,
    slug: 'trafficsense',
    title: 'TrafficSense - Smart Acoustic Traffic Monitoring',
    category: 'IoT',
    year: '2024',

    description:
      'An IoT-enabled smart traffic density monitoring system utilizing ESP32, acoustic sound sensors, and Google Cloud Firestore to detect roadway congestion and stream real-time telemetry to an interactive web dashboard.',

    overview:
      'TrafficSense ("Deteksi Cepat, Lalu Lintas Tepat") is an intelligent traffic monitoring and density classification IoT platform developed by Muhammad Daffa Husen. Powered by an ESP32 microcontroller and high-sensitivity acoustic sound sensors deployed at urban roadways (such as JL. Teuku Nyak Arief, Universitas Syiah Kuala), the system captures ambient sound levels, calculates moving acoustic averages, and synchronizes real-time telemetry to Google Cloud Firestore. The web dashboard provides dynamic congestion categorization ("Lancar", "Sedang", "Padat"), interactive Google Maps geospatial tracking, and historical traffic analytics.',

    problem:
      'Conventional road traffic monitoring depends heavily on expensive CCTV networks or manual patrols that require high network bandwidth, are vulnerable to poor lighting/weather conditions, and lack automated acoustic signal awareness for immediate density estimation.',

    solution:
      'Engineered a cost-effective acoustic IoT sensing node using ESP32 with Wi-Fi telemetry and NTP time synchronization. Sensor analog values are processed and pushed to Cloud Firestore, which drives a responsive real-time web dashboard featuring live congestion alerts, geospatial map overlays, and sensor history charts.',

    features: [
      'ESP32 Microcontroller & Wi-Fi Telemetry: Low-power edge computing node collecting real-time analog sound sensor samples with NTP time synchronization',
      'Cloud Firestore Real-Time Database: Instantaneous data synchronization between edge IoT hardware nodes and web client dashboards',
      'Acoustic Traffic Density Estimation: Algorithms mapping ambient decibel and sensor ADC values into intuitive congestion states (Lancar, Sedang, Padat)',
      'Interactive Web Dashboard & Google Maps: Embedded geospatial visualization with road coordinate tracking (JL. Teuku Nyak Arief, Banda Aceh)',
      'Historical Data Logging & Analytics: Dedicated history page displaying chronological traffic trends and peak hour acoustic sensor readings',
      'Responsive Mobile-First UI: Clean, modern interface optimized for field monitoring and traffic authority dispatch'
    ],

    role: 'Full-Stack IoT Developer & Embedded Engineer',

    image: '/projects/trafficsense/trafficsense-mockup.png',
    images: [
      {
        src: '/projects/trafficsense/trafficsense-mockup.png',
        caption: 'TrafficSense Dual Device Mockup (Welcome Splash & Live Traffic Dashboard)'
      },
      {
        src: '/projects/trafficsense/trafficsense-dashboard.jpg',
        caption: 'Live Traffic Monitoring Dashboard with Google Maps (JL. Teuku Nyak Arief) & Real-Time Sensor Telemetry'
      },
      {
        src: '/projects/trafficsense/trafficsense-splash.png',
        caption: 'TrafficSense Mobile Splash Screen ("Deteksi Cepat, Lalu Lintas Tepat")'
      },
      {
        src: '/projects/trafficsense/trafficsense-logo-3d.png',
        caption: 'TrafficSense 3D GPS Location Pin & Smart Mobility Identity'
      }
    ],

    technologies: [
      'ESP32',
      'IoT',
      'Acoustic Sound Sensor',
      'Firebase Firestore',
      'Google Maps API',
      'JavaScript',
      'Bootstrap 5',
      'Embedded C/C++',
      'NTP Protocol'
    ],

    links: {
      github: 'https://github.com/dappahsn/TrafficSense',
      demo: 'https://dappahsn.github.io/TrafficSense/'
    },

    featured: false
  },

  {
    id: 10,
    slug: '3d-reconstruction-meshroom',
    title: 'Interactive 3D Electronics with Meshroom & Three.js',
    category: 'Web',
    year: '2024',

    description:
      'Interactive web platform visualizing 3D reconstructed electronic components (Arduino Uno, Breadboard, LCD) using Meshroom photogrammetry and Three.js for real-time educational exploration.',

    overview:
      'An interactive 3D computer graphics and photogrammetry project developed under the supervision of Kahlil Muchtar, Ph.D. at Universitas Syiah Kuala. The system transforms multi-angle physical photographs of real-world electronic components (Arduino Uno board, prototyping breadboard, and 16x2 I2C LCD module) into textured 3D mesh models via Meshroom (AliceVision), rendering them in an interactive Three.js web viewport to support accessible hardware and electronics education.',

    problem:
      'Understanding physical electronics hardware in remote or resource-limited learning settings is challenging without physical lab equipment, while traditional manual 3D modeling is labor-intensive and often lacks realistic material texturing.',

    solution:
      'Built an end-to-end 3D digitization and web rendering pipeline: capturing multi-view high-resolution photography, computing camera poses & dense point clouds with AliceVision SfM (Structure from Motion), generating high-fidelity GLB 3D meshes with texture projection in Meshroom, and implementing an interactive Three.js web application with orbit controls, dynamic lighting, and component inspection.',

    features: [
      'Multi-Angle Photogrammetry Pipeline: Captured multi-perspective photographic datasets of real electronic hardware for automated 3D reconstruction',
      'Dense Mesh & Texture Generation: Utilized AliceVision framework in Meshroom to compute depth maps, surface meshing, and high-resolution texture UV unwrapping',
      'Reconstructed Electronic Hardware: 3D interactive models of Arduino Uno microcontroller, prototyping breadboard, and 16x2 Character LCD display',
      'Real-Time Three.js Web Viewport: Smooth orbit camera rotation, zoom, pan, and real-time lighting rendering directly in modern web browsers',
      'Educational Hardware Platform: Provides an interactive visual tool for students and educators to inspect component pinouts and spatial layouts',
      'Academic Research Collaboration: Guided by supervisor Kahlil Muchtar, Ph.D., advancing computer graphics and digital twin learning'
    ],

    role: 'Computer Graphics & Web 3D Developer',

    image: '/projects/meshroom/meshroom-hero.jpg',
    images: [
      {
        src: '/projects/meshroom/meshroom-hero.jpg',
        caption: 'Interactive 3D Computer Graphics Web Portal (Hero & Overview)'
      },
      {
        src: '/projects/meshroom/meshroom-3d-models.png',
        caption: 'Interactive 3D Reconstructed Hardware Models (Breadboard & Arduino Uno)'
      },
      {
        src: '/projects/meshroom/meshroom-team.png',
        caption: 'Project Team & Contributor Directory (Muhammad Daffa Husen)'
      },
      {
        src: '/projects/meshroom/meshroom-3d-electronics-display.jpg',
        caption: 'Photogrammetry 3D Reconstruction & Multi-Angle Alignment Pipeline'
      }
    ],

    technologies: [
      'Three.js',
      'Meshroom',
      'AliceVision',
      'Photogrammetry',
      'WebGL',
      'JavaScript',
      'GLB / 3D Modeling',
      'Bootstrap 5',
      'Computer Graphics'
    ],

    links: {
      github: 'https://github.com/dappahsn/Group-2---Computer-Graphics',
      demo: 'https://dappahsn.github.io/Group-2---Computer-Graphics/'
    },

    featured: false
  },

  {
    id: 11,
    slug: 'lifegen-health-companion',
    title: 'LifeGen - Health & Fitness Companion',
    category: 'UI/UX Design',
    year: '2023',

    description:
      'National-level UI/UX competition finalist project at INFEST 9.0. A health and wellness companion mobile app designed in Figma with intuitive user flows, calorie tracking, food logging, and daily activity monitoring.',

    overview:
      'LifeGen is a modern health and fitness companion mobile application designed to empower users to build sustainable lifestyle habits. Developed as a national finalist entry for the UI/UX Design Competition at INFEST 9.0 (Informatics Festival), the project encompasses full-cycle product design—from empathy-driven user research and wireframing to high-fidelity interactive prototyping and design systems in Figma.',

    problem:
      'Many individuals struggle to maintain consistent fitness routines due to overwhelming, complicated tracking apps with steep learning curves, cluttered user interfaces, and lack of motivational habit-forming feedback.',

    solution:
      'Designed an intuitive, motivating mobile experience centered on four core pillars: calorie tracking (burned vs. consumed), step counting with daily milestones, frictionless meal logging, and progress insights. Designed in Figma with a full interactive prototype flow from onboarding to daily dashboard tracking.',

    features: [
      'National Finalist Recognized: Selected as Finalist in the national UI/UX Design Competition at INFEST 9.0 (Informatics Festival 2023)',
      'Comprehensive Figma Interactive Prototype: Fully interactive prototyping flow including onboarding questionnaire, authentication, dashboard, meal logging diary, and profile settings',
      'Calorie & Nutrition Tracking: Intuitive calorie ring visualization comparing daily calories consumed against active energy burned',
      'Step & Activity Monitoring: Daily step goal progress bars with distance, active minutes, and milestone badges',
      'Food & Meal Intake Logging: Quick-add food journal with nutritional macro breakdown (carbs, proteins, fats)',
      'Design System & Component Library: Structured Figma components, cohesive energetic orange brand identity, accessible typography, and mobile UX standards'
    ],

    role: 'Lead UI/UX Designer & Product Researcher',

    image: '/projects/lifegen/lifegen-mockup.png',
    images: [
      {
        src: '/projects/lifegen/lifegen-mockup.png',
        caption: 'LifeGen Mobile App Dual Device Mockup (Splash & Dashboard UI)'
      },
      {
        src: '/projects/lifegen/lifegen-dashboard.png',
        caption: 'LifeGen Main Dashboard - Calorie Ring Tracker, Daily Foot Steps & Activity Metrics'
      },
      {
        src: '/projects/lifegen/lifegen-splash.png',
        caption: 'LifeGen Splash & Brand Launch Screen Interface'
      },
      {
        src: '/projects/lifegen/lifegen-figma-flow.png',
        caption: 'Figma Interactive Prototyping Flow Map & Information Architecture'
      },
      {
        src: '/projects/lifegen/lifegen-competition-finalist.png',
        caption: 'National UI/UX Finalist Presentation at INFEST 9.0 (Informatics Festival 2023)'
      }
    ],

    technologies: [
      'Figma',
      'UI/UX Design',
      'Interactive Prototyping',
      'User Research',
      'Design Thinking',
      'Design Systems',
      'Wireframing',
      'Mobile UX'
    ],

    links: {
      github: '',
      demo: 'https://www.figma.com/proto/MIYprCXiJ8d9SDMZA5kMYT/Lifegen?page-id=0%3A1&node-id=48-3636&p=f&viewport=488%2C591%2C0.18&t=ywpG479uWKZQYhzF-1&scaling=scale-down&content-scaling=fixed&starting-point-node-id=48%3A3636&show-proto-sidebar=1'
    },

    featured: true
  },

  {
    id: 12,
    slug: 'veggieneed',
    title: 'VeggieNeed - Farm-to-Table Marketplace',
    category: 'UI/UX Design',
    year: '2023',

    description:
      'A conceptual farm-to-table digital marketplace designed in Figma connecting local vegetable growers and farmers directly with consumers through an intuitive, accessible mobile UI/UX experience.',

    overview:
      'VeggieNeed is a user-centered mobile marketplace application designed to bridge the gap between local agricultural farmers and urban households. Developed under the mentorship of Rahmad Dawood at Universitas Syiah Kuala, the project translates comprehensive user research and persona modeling into intuitive wireframes and interactive Figma prototypes, facilitating seamless crop discovery, direct producer purchasing, and community-driven fair trade.',

    problem:
      'Smallholder vegetable farmers often struggle with unfair intermediary markups and limited market access, while conscious consumers find it difficult to source fresh, affordable, and ethically grown local organic produce.',

    solution:
      'Designed an accessible, community-oriented mobile platform in Figma featuring categorized harvest search ("Cari Hasil Panen"), promotional seasonal bundles ("Plenti Plenti" & "VegDiet"), direct farmer messaging, streamlined cart checkout, and clear order tracking flows.',

    features: [
      'Farm-to-Table Discovery: Categorized marketplace browsing with instant harvest search and agricultural category filters',
      'Interactive Figma High-Fidelity Prototype: Seamless end-to-end user journeys from splash onboarding to product checkout and order management',
      'Promotional & Seasonal Campaign Feeds: Engaging promotional cards and curated dietary bundles (e.g. "Plenti Plenti", "VegDiet")',
      'Direct Buyer-Seller Communication: Integrated chat and inquiry channels fostering direct community relationships with local farmers',
      'Accessible UI Design System: Organic green visual identity, high-contrast readable typography, and intuitive mobile ergonomics',
      'User-Centered Design Methodology: Grounded in empathy research, user personas, and iterative wireframe usability testing'
    ],

    role: 'Lead UI/UX Designer & Product Researcher',

    image: '/projects/veggieneed/veggieneed-mockup.png',
    images: [
      {
        src: '/projects/veggieneed/veggieneed-mockup.png',
        caption: 'VeggieNeed Mobile App Dual Device Mockup (Splash & Marketplace UI)'
      },
      {
        src: '/projects/veggieneed/veggieneed-home.png',
        caption: 'VeggieNeed Marketplace Home Screen - Harvest Search, Promotional Banners & Navigation'
      },
      {
        src: '/projects/veggieneed/veggieneed-splash.png',
        caption: 'VeggieNeed Onboarding & Brand Splash Screen ("Kenali petani Anda, kenali makanan Anda")'
      },
      {
        src: '/projects/veggieneed/veggieneed-logo.png',
        caption: 'VeggieNeed Brand Identity & Organic Leaf Shopping Cart Logo'
      }
    ],

    technologies: [
      'Figma',
      'UI/UX Design',
      'Interactive Prototyping',
      'User Research',
      'Design Systems',
      'Wireframing',
      'Persona Building',
      'Mobile UX'
    ],

    links: {
      github: '',
      demo: 'https://www.figma.com/proto/GJKRbnFwVvOZUCtw7SbOdc/Veggieneed?page-id=0%3A1&node-id=1685-3385&p=f&viewport=496%2C172%2C0.31&t=f0GegnLakMD2UK4A-1&scaling=scale-down&content-scaling=fixed&starting-point-node-id=104%3A252'
    },

    featured: false
  }
];

// ─── LEGACY COMPAT (kept for existing journey section references) ──────────────
export const experience = [
  ...experiences,
  ...education.map(e => ({ ...e, role: e.degree, company: e.institution })),
  ...organizations.map(o => ({ ...o, company: o.organization }))
];
