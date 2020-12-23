insert into m_action_flow (id, start_action, end_action) values (1, (select id from m_action where code = '/index.xhtml'), (select id from m_action where code = '/auto/list.xhtml'));

insert into m_action_flow (id, start_action, end_action) values (2, (select id from m_action where code = '/index.xhtml'), (select id from m_action where code = '/polita/list.xhtml'));

insert into m_action_flow (id, start_action, end_action) values (3, (select id from m_action where code = '/auto/list.xhtml'), (select id from m_action where code = '/index.xhtml'));
commit;